package com.aleangelozi.mysamplechatappkotlin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import zendesk.chat.*
import zendesk.messaging.MessagingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Chat.INSTANCE.init(this, "jkkJEDT18TAduH0eOJ2fL422Etj1IJxR",
            "a0ec348c439fef74d030281ba454857d3f4583c8d2756064")

        val mChatButton = findViewById<Button>(R.id.chat_button)

        mChatButton.setOnClickListener {
            initializeChatSdk()
        }
    }

    private fun initializeChatSdk() {

        val profileProvider = Chat.INSTANCE.providers()?.profileProvider()

        val visitorInfo = VisitorInfo.builder()
            .withName("Renato")
            .build()

        profileProvider?.setVisitorInfo(visitorInfo,null)


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

}