# CPU & Performance

Os testes para avaliar o consumo de CPU do nosso aplicativo foram feito sobre as seguintes telas: **Tela de perfil**, 
**Tela de  livros favoritos**, **tela de mensagem**,**busca de livros** e **busca de usuários**. Para medir o consumo 
de CPU foi utilizado a ferramenta *Profiler* do *Android Studio*.

* Tela de perfil:
<img src="img/cpu tela de perfil.png" alt="Tela de perfil" />

* Tela com os livros favoritos
<img src="img/cpu tela de livros favoritos.png" alt="Tela de livros favoritos" />

* tela de mensagem
<img src="img/cpu tela de mensagem.png" alt="Tela de mensagem" />

* buscas de livros
<img src="img/cpu tela de buscar livros.png" alt="Tela de livros" />

* buscas de usuários
<img src="img/cpu tela de busca de usuario.png" alt="Tela de usuários" />


# Técnicas Utilizadas

### Recycler View
Todas as listas da aplicação foram feitas usando Recycler Views, uma maneira mais performática e padronizada de exibir listas.

***TweetAdapter***
~~~ Kotlin
class UserTweetAdapter(private val tweetList: MutableList<Tweet>, private val ctx: Context) : RecyclerView.Adapter<UserTweetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.recycler_tweet_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = tweetList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val tweet = tweetList[position]

        holder.tweetText.text = tweet.text

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tweetText = itemView.tweet_txt
    }
    
    fun clearAll() {
        val size = itemCount
        tweetList.removeAll { true }
        notifyItemRangeRemoved(0, size)
    }

    fun addNewList(newList: List<Tweet>) {
        newList.forEach {
            tweetList.add(it)
        }
        notifyItemRangeInserted(0, itemCount)
    }

}
~~~

***UserProfileFragment***
~~~kotlin
class UserProfileFragment : Fragment() {

...

// Tweet Recycler View
        view.recycler_user_tweet.adapter = UserTweetAdapter(mutableListOf(), context!!)
        view.recycler_user_tweet.layoutManager = LinearLayoutManager(context)
...

}

~~~

***Layout***
~~~xml
        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/recycler_user_tweet"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
~~~

