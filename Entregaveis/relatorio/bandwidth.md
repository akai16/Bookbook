# Consumo de Rede

Os testes para avaliar o consumo de rede do nosso aplicativo foram feito sobre as seguintes telas: **Tela de perfil**, 
**Tela de  livros favoritos**, **tela de mensagem**,**busca de livros** e **busca de usuários**. Para medir o consumo 
de rede foi utilizado a ferramenta *Profiler* do *Android Studio*. Como recurso de otimização de rede foi utilizado a biblioteca do **Picasso** para tratar todo o gerenciamento das imagens(como: memória automática, cache de disco e reciclagem download). Além disso, foi utilizado o **Retrofit** para fazer as requisições http.

### Testes utilizando a ferramenta Profiler

* Tela de perfil:
<img src="img/rede tela de perfil.png" alt="Tela de perfil" />

* Tela com os livros favoritos
<img src="img/rede tela de perfil.png" alt="Tela de livros favoritos" />

* tela de mensagem
<img src="img/rede tela de mensagem.png" alt="Tela de mensagem" />

* buscas de livros
<img src="img/rede tela de buscar livros.png" alt="Tela de livros" />

* buscas de usuários
<img src="img/rede tela de busca de usuario.png" alt="Tela de usuários" />

### Trecho de código de utimização de Rede

* Importanto a dependência do Picasso:

~~~Kotlin
dependencies {
    // Picasso
    implementation 'com.squareup.picasso:picasso:2.71828'
    
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
}
~~~

* Trecho de códito utilizando o Picasso:

Esse trecho se encontra no arquivo *BookSearchAdapter.kt*
~~~Kotlin
// Set Book Cover Image
Picasso.get().load(book.image).into(holder.image, object: Callback {
    override fun onSuccess() {
        Log.d(Consts.DEBUG_TAG, "Imagem baixada")
    }

    override fun onError(e: Exception?) {
        e!!.printStackTrace()
        Log.d(Consts.DEBUG_TAG, "Erro ao baixar imagem")
    }

})
~~~

* Trecho de códito utilizando o Retrofit:


