package com.example.bdd_my_sql
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class Connexion(url: String, username: String, password: String) {
    private lateinit var conn: Connection
    private val URL = url
    private val USERNAME = username
    private val PASSWORD = password

    init {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
        } catch (ex: ClassNotFoundException) {
            println("erreur com.mysql.cj.jdbc.Driver")
        }
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)
            println("connexion OK")
        } catch (ex: SQLException) {
            println("erreur de connexion à la bdd")
        }
    }

    fun getConnexion(): Connection {
        return conn
    }
}
