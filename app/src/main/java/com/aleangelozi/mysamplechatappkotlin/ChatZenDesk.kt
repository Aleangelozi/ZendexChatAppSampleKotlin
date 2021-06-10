package com.aleangelozi.mysamplechatappkotlin

import android.content.Context
import android.util.Log
import zendesk.chat.*
import zendesk.messaging.MessagingActivity

class ChatZenDesk {
    private var visitorSet = false

    fun initializeChatSdk(context: Context) {

        initVisitorInfo()

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
            .show(context,chatConfiguration)
    }

    private fun initVisitorInfo() {
        val name = "Alessandro Angelozi Ribeiro"
        val email = "whatever@whatever.com"

        val profileProvider = Chat.INSTANCE.providers()?.profileProvider()

        val observationScope = ObservationScope()
        Chat.INSTANCE.providers()!!.chatProvider()
            .observeChatState(observationScope, object : Observer<ChatState?> {
                override fun update(t: ChatState?) {
                    val chatStatus = t?.chatSessionStatus
                    // Status achieved after the PreChatForm is completed
                    if (chatStatus == ChatSessionStatus.INITIALIZING) {
                        // Update the information MID chat here. All info but Department can be updated
                        if (!visitorSet) {
                            // Add here the code to set the visitor info - visitorInfo would be
                            // a VisitorInfo type variable containing all the information to set

                            profileProvider?.setVisitorInfo(setVisitorInfo(name, email), null)
                            visitorSet = true
                        }
                    } else {

                        profileProvider?.setVisitorInfo(setVisitorInfo(name, email), null)
                        visitorSet = true
                        // There are few other statuses that you can observe but they are unused in this example
                        Log.d(
                            "DEBUG",
                            "[observerSetup] - ChatSessionUpdate -> (unused) status : $chatStatus"
                        )
                    }
                }
            })

    }
        private fun setVisitorInfo(name: String, email: String) : VisitorInfo {

            return VisitorInfo.builder()
                .withName(name)
                .withEmail(email)
                .build()
        }


}