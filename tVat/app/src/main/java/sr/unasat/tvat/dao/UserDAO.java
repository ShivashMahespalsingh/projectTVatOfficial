package sr.unasat.tvat.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sr.unasat.tvat.entities.User;

/**
 * Created by mahes on 2/10/2017.
 */

public class UserDAO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public static final String ID = "id";
    public static final String USER_TABLE = "user";
    public static final String USER_FIRSTNAME = "firstname";
    public static final String USER_LASTNAME = "lastname";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";

//achternaam,voornaam,gebruikersnaam,wachtwoord,emailadres
// private static final String SQL_USER_TABLE_QUERY = "create table user(id INTEGER PRIMARY KEY, username STRING NOT NULL UNIQUE, password STRING NOT NULL)";

   private static final String SQL_USER_TABLE_QUERY = "create table user(id INTEGER PRIMARY KEY, firstname STRING NOT NULL, lastname STRING NOT NULL, username STRING NOT NULL UNIQUE, password STRING NOT NULL UNIQUE, email STRING NOT NULL UNIQUE)";

    public UserDAO(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        setDefaultCredentials();
    }
    private void setDefaultCredentials(){
        User user = findOneRecordByUsername("admin");
        if (user != null){
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_FIRSTNAME,"Shivash");
        contentValues.put(USER_LASTNAME,"Mahespalsingh");
        contentValues.put(USER_USERNAME,"admin");
        contentValues.put(USER_PASSWORD,"admin");
        contentValues.put(USER_EMAIL,"mahespalsingh.shivash@outlook.com");
        insertOnerecord(USER_TABLE,contentValues);
    }

    public User getUserByUsername(String name){
        User user = null;
        Cursor cursor = null;
        SQLiteDatabase db= getReadableDatabase();
        cursor = db.query(USER_TABLE, null,
                USER_USERNAME+" = ?", new String[] { "" + name },null,null,null);

        if (cursor.moveToFirst()) {

            long user_id = cursor.getLong(cursor.getColumnIndex(ID));
            String firstname = cursor.getString(cursor.getColumnIndex(USER_FIRSTNAME));
            String lastname= cursor.getString(cursor.getColumnIndex(USER_LASTNAME));
            String username = cursor.getString(cursor.getColumnIndex(USER_USERNAME));
            String password= cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
            String email = cursor.getString(cursor.getColumnIndex(USER_EMAIL));



            user = new User(user_id,firstname,lastname,username,password,email);

        }
        db.close();
        return user;
    }

    public void setUser(UserDAO db,String firstname, String lastname, String username, String password, String email){
             SQLiteDatabase db2 = db.getWritableDatabase();
        ContentValues userValues = new ContentValues();

        userValues.put(USER_FIRSTNAME,firstname);
        userValues.put(USER_LASTNAME,lastname);
        userValues.put(USER_USERNAME,username);
        userValues.put(USER_PASSWORD,password);
        userValues.put(USER_EMAIL,email);

        db2.insert(USER_TABLE,null,userValues);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(SQL_USER_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertOnerecord(String tableName, ContentValues contentValues){
        SQLiteDatabase db  = getWritableDatabase();
        long rowId = db.insert(tableName,null,contentValues);
        db.close();
        return rowId;
    }
    public User findOneRecordByUsername(String username){
        User user = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s", USER_TABLE);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5));
        }
        db.close();
        return user;
    }




}


