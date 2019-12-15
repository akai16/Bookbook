# Architecture Components

# ViewModel and LiveData

O uso de ViewModel em conjunto com LiveData foi usado para melhorar a parte arquitetural da aplicação, além de evitar leaks e crashes que
causados por requisições web assincronas que agiam sobre os componetes da interface. 

### ProfileViewModel
Responsável por adquirir as informações de perfil do usuário (Nome, livros favoritos, mensagens...)
~~~kotlin
class ProfileViewModel : ViewModel() {

    var changeNotifier = MutableLiveData<User>()
    var tweetNotifier = MutableLiveData<List<Tweet>>()

    private val db = FirebaseFirestore.getInstance()


    fun fetchUserData(userID: String) {

        db.collection(FirebaseConsts.USERS_COLLECTION).document(userID)
            .get()
            .addOnSuccessListener {
                changeNotifier.value = User.convertToUser(userID, it)
            }
            .addOnFailureListener {
                Log.d(Consts.DEBUG_TAG, "ProfileViewModel -> Failed on fetching user")
            }
    }

    fun fetchUserTweets(userID: String) {
        db.collection(FirebaseConsts.USERS_COLLECTION).document(userID)
            .get()
            .addOnSuccessListener {
                val user = User.convertToUser(userID, it)
                val tweetList = user.tweetList
                tweetNotifier.value = tweetList
            }
            .addOnFailureListener {
                Log.d(Consts.DEBUG_TAG, "ProfileViewModel -> Failed on fetching user tweets")
            }
    }

}
~~~

### UserProfileFragment
Envia eventos e recebe informações do ProfileViewModel
~~~kotlin
class UserProfileFragment : Fragment() {
  ...
   // ViewModels
    private val profileViewModel : ProfileViewModel by lazy {
        ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    // Observers
    private val profileObserver = Observer<User> { displayUserInformation(it) }
    private val tweetObserver = Observer<List<Tweet>> { updateTweetList(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.userID = arguments!!.getString(EXTRA_USER_ID) ?: "Null"

        this.profileViewModel.changeNotifier.observe(this, profileObserver)
        this.profileViewModel.fetchUserData(this.userID!!)

        this.profileViewModel.tweetNotifier.observe(this, tweetObserver)
    }
~~~





# Room
 
Em versões anteriores do aplicativo, a biblioteca Room estava sendo empregada para persistir dados relacionados ao Usuário
e seus livros de interesse. O código desenvolvido pode ser visto no commit ["ea0df4"](https://github.com/akai16/Bookbook/tree/ea0df42b343085e5e9e2a45f6292f748ad1e9cc9)


### Room Database
~~~kotlin
@Database(entities = [
    User::class,
    Author::class,
    Book::class,
    BookAuthorEntity::class,
    FavBooksEntity::class,
    ReadBooksEntity::class
], version = 1, exportSchema = false)
abstract class BookbookDB : RoomDatabase() {

    abstract fun authorDAO() : AuthorDAO
    abstract fun bookDAO() : BookDAO
    abstract fun userDAO() : UserDAO

    companion object {

        private var INSTANCE: BookbookDB? = null

        fun getDatabase(ctx: Context) : BookbookDB {

            if (INSTANCE == null) {
                synchronized(BookbookDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        ctx.applicationContext,
                        BookbookDB::class.java,
                        "bookbook.db"
                    ).addCallback(PrePopulateDB())
                        .build()
                }
            }

            return INSTANCE!!
        }
    }

}
~~~

## User DAO
~~~kotlin
@Dao
interface UserDAO {

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE ${User.ID_COLUMN} = :userID")
    fun getUserByID(userID: Int): User

    @Query(
        "SELECT Book.* " +
        " FROM ${Book.TABLE_NAME} as Book " +
        "LEFT JOIN ${FavBooksEntity.TABLE_NAME} as FavBook " +
        "ON Book.${Book.ID_COLUMN} = FavBook.${FavBooksEntity.BOOK_ID_COLUMN} " +
        "WHERE ${FavBooksEntity.BOOK_ID_COLUMN} = :userID "
    )
    fun getUserFavBooks(userID: Int): List<Book>

    @Query(
        "SELECT Book.* FROM ${Book.TABLE_NAME} as Book " +
        "LEFT JOIN ${ReadBooksEntity.TABLE_NAME} as ReadBook " +
        "ON Book.${Book.ID_COLUMN} = ReadBook.${ReadBooksEntity.BOOK_ID_COLUMN} " +
        "WHERE ReadBook.${ReadBooksEntity.USER_ID_COLUMN} = :userID "
    )
    fun getUserReadBooks(userID: Int): List<Book>


    @Insert(entity = User::class, onConflict = OnConflictStrategy.ABORT)
    fun createUser(newUser: User)

    @Insert(entity = FavBooksEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun addBookToUserFavBooks(favBookRow: FavBooksEntity)

    @Insert(entity = ReadBooksEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun addBookToUserReadBooks(readBookRow: ReadBooksEntity)
}
~~~

## User Entity
~~~kotlin
@Entity(tableName=User.TABLE_NAME)
class User(
    @ColumnInfo(name=USERNAME_COLUMN) val username: String,
    @ColumnInfo(name=NAME_COLUMN) val name:String
) {

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name=ID_COLUMN)
    var id: Int = -1

    companion object {
        const val TABLE_NAME = "user_table"
        const val ID_COLUMN = "id"
        const val USERNAME_COLUMN = "username"
        const val NAME_COLUMN = "name"
    }

}
~~~
