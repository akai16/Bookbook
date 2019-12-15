# Acessibilidade

Para o estudo da acessibilidade, foi utilizada a ferramenta Axe Android.

A ferramenta encontrou algumas violações a regras definidas para acessibilidade como mostrado abaixo

### Tela de perfil
***O EditView clicavel não possuia o tamanho minimo requerido.***<br>

<img src="img/acessibilidade_tela_perfil_favorite.jpeg" width=400 alt="Tela de Perfil" align=center />

<br>Para correção, a fonte foi aumentada<br>

~~~xml
                <TextView
                    android:id="@+id/txt_fav_books"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginStart="8dp"
                    android:layout_marginEnd="8dp"
                    android:textSize="18sp"
                    android:text="@string/favorite_books" />
~~~


### Tela de Mensagem
<br>***A view clicavel deve ter um atributo Name definido.***<br>

<img src="img/acessibilidade_tela_mensagem_X.jpeg" width=400 alt="Tela de Perfil" />

<br> Correção: <br>
~~~xml
<com.google.android.material.button.MaterialButton
        android:id="@+id/messageCancel"
        android:layout_width="53dp"
        android:layout_height="50dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        android:layout_marginBottom="62dp"
        android:text="@string/back"
        style="@style/Widget.MaterialComponents.Button.Icon"
        app:icon="@android:drawable/ic_menu_close_clear_cancel"
        app:layout_constraintBottom_toTopOf="@+id/comment"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
~~~

<br>***View com valores modificaveis devem estar acompanhadas por um label.***<br>

<img src="img/acessibilidade_tela_mensagem_comentario.jpeg" width=400 alt="Tela de Perfil" />

Correção:<br>
~~~xml
<TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:contentDescription="@string/fazer_um_coment_rio"
        android:labelFor="@+id/comment"
        android:text="@string/comentar"
        app:layout_constraintBottom_toTopOf="@+id/comment"
        app:layout_constraintStart_toStartOf="parent" />

    <EditText
        android:id="@+id/comment"
        android:layout_width="match_parent"
        android:layout_height="262dp"
        android:layout_marginTop="128dp"
        android:ems="10"
        android:gravity="start|top"
        android:hint="@string/fa_a_um_coment_rio"
        android:inputType="textMultiLine"
        android:lines="5"
        android:maxLines="10"
        android:scrollbars="vertical"
        android:padding="16dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.0" />
~~~


### Tela de Pesquisa
<br>***View com valores modificaveis devem estar acompanhadas por um label.***<br>
<img src="img/acessibilidade_tela_pesquisa_edit.jpeg" width=400 alt="Tela de Perfil" />

Correção:<br>
~~~xml
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:labelFor="@+id/edit_book_search"
            android:text="@string/search" />

        <EditText
            android:id="@+id/edit_book_search"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_gravity="top"
            android:layout_marginTop="64dp"
            android:layout_weight="4" />
~~~
