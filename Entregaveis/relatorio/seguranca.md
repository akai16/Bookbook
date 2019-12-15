# Segurança

Os métodos de autenticação usados foram da bibliboteca de autenticação do Firebase, FirebaseAuth, que se encarrega de 
todo o processo de cadastro e obtenção do token para acesso do usuário.

Nenhuma parte da aplicação pode ser acessada sem a autenticação do usuário, mantendo a boa prática de mostrar informações 
sensivéis somente para usuários autenticados.

~~~kotlin
class LoginActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Firebase Auth
        val mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            goToProfilePage()
        }
        else {
            showSignInOptions()
        }

    }
    
    ...
    
~~~
