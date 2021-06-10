package com.aleangelozi.mysamplechatappkotlin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mChatButton = findViewById<Button>(R.id.chat_button)

        val chat = ChatZenDesk(this)
        chat.initChatZenDesk()

        mChatButton.setOnClickListener {

            chat.openChat()

        }

    }

}