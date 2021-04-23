package com.aleangelozi.mysamplechatappkotlin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import zendesk.chat.Chat
import zendesk.chat.ChatEngine
import zendesk.messaging.MessagingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Chat.INSTANCE.init(this, "jkkJEDT18TAduH0eOJ2fL422Etj1IJxR")

        val mChatButton = findViewById<Button>(R.id.chat_button)

        mChatButton.setOnClickListener {
            initializeChatSdk()
        }
    }

    private fun initializeChatSdk(){
        MessagingActivity.builder()
                .withEngines(ChatEngine.engine())
                .show(this)
    }
}