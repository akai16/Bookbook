package com.example.bookbook.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


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