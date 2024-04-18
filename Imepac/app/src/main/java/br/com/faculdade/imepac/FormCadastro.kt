package br.com.faculdade.imepac

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class FormCadastro : AppCompatActivity() {

    private lateinit var edit_nome : EditText
    private lateinit var edit_email: EditText
    private lateinit var edit_senha : EditText
    private lateinit var btn_Cadastrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro)
        getSupportActionBar()?.hide()

        edit_nome = findViewById(R.id.edit_nome)
        edit_email = findViewById(R.id.edit_email)
        edit_senha = findViewById(R.id.edit_senha)
        btn_Cadastrar = findViewById(R.id.btn_cadastrar)

        btn_Cadastrar.setOnClickListener {
            val nome = edit_nome.text.toString().trim()
            val email = edit_email.text.toString().trim()
            val senha = edit_senha.text.toString().trim()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                val mensagemErro = "Campos inseridos devem ser preenchidos!"
                val snackbar = Snackbar.make(it, mensagemErro, Snackbar.LENGTH_LONG)
                snackbar.show()
            } else {
                cadastrarUsuario(it)
            }
        }
    }
        fun cadastrarUsuario(it: View){
            val email = edit_email.text.toString().trim()
            val senha = edit_senha.text.toString().trim()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener{task-> if (task.isSuccessful){
                val mensagemOk = "Cadastro realizado com sucesso!"
                val snackbar = Snackbar.make(it, mensagemOk, Snackbar.LENGTH_LONG)
                snackbar.show()
            } else {
                val mensagemErro = "Erro ao cadastrar usuário!"
                val snackbar = Snackbar.make(it, mensagemErro, Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tela_cadastro)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}