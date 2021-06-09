package com.aleangelozi.mysamplechatappkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import zendesk.chat.*
import zendesk.messaging.MessagingActivity


private const val TAG = "ActivityOverrides"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Chat.INSTANCE.init(this, BuildConfig.ZENDESK_CHAT_ACCOUNT_KEY,
            BuildConfig.ZENDEX_APP_ID)

        val mChatButton = findViewById<Button>(R.id.chat_button)

        mChatButton.setOnClickListener {

            initializeChatSdk()
        }

        Log.d(TAG, "onCreate")
    }

    private fun initializeChatSdk() {

        initializeVisitorInfo()

        val chatConfiguration = ChatConfiguration.builder()
                .withPreChatFormEnabled(true)
                .withTranscriptEnabled(false)
                .withNameFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .withEmailFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .withPhoneFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .withDepartmentFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .build()

        MessagingActivity.builder()
                .withEngines(ChatEngine.engine())
                .show(this,chatConfiguration)
    }

    private fun initializeVisitorInfo() {
        var visitorSet = false

        val name = "Alessandro Angelozi Ribeiro"

        val profileProvider = Chat.INSTANCE.providers()?.profileProvider()

        fun setupObserver() {
            val observationScope = ObservationScope()
            Chat.INSTANCE.providers()!!.chatProvider()
                .observeChatState(observationScope, object : Observer<ChatState?> {
                    override fun update(t: ChatState?) {
                        val chatStatus = t?.chatSessionStatus
                        // Status achieved after the PreChatForm is completed
                        if (chatStatus == ChatSessionStatus.STARTED) {
                            // Update the information MID chat here. All info but Department can be updated
                            if (!visitorSet) {
                                // Add here the code to set the visitor info - visitorInfo would be a VisitorInfo type variable containing all the information to set
                                val visitorInfo = VisitorInfo.builder()
                                    .withName(name)
                                    .withEmail("whatever@whatever.com")
                                    .build()
                                profileProvider?.setVisitorInfo(visitorInfo, null)
                                visitorSet = true
                            }
                        } else {
                            val visitorInfo = VisitorInfo.builder()
                                .withName(name)
                                .withEmail("whatever@whatever.com")
                                .build()

                            profileProvider?.setVisitorInfo(visitorInfo,null)
                            // There are few other statuses that you can observe but they are unused in this example
                            Log.d(
                                "DEBUG",
                                "[observerSetup] - ChatSessionUpdate -> (unused) status : $chatStatus"
                            )
                        }
                    }
                })
        }

        setupObserver()

    }



}