package com.aleangelozi.mysamplechatappkotlin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import zendesk.chat.Chat
import zendesk.chat.ChatConfiguration
import zendesk.chat.ChatEngine
import zendesk.chat.PreChatFormFieldStatus
import zendesk.messaging.MessagingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Chat.INSTANCE.init(this, "jkkJEDT18TAduH0eOJ2fL422Etj1IJxR")

        val mChatButton = findViewById<Button>(R.id.chat_button)

        mChatButton.setOnClickListener {
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
}