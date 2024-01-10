package com.example

import com.example.bdd_my_sql.Gestion
import com.example.model.Valeur

class Requettes {


    fun getValeur(id:Int): Valeur? {
        var laConnexion = Gestion().laConnexion
        var valeur: Valeur? = null
        var prepStatement = laConnexion.getConnexion()!!
            .prepareStatement("select * from valeurs where id=?")
        prepStatement.setInt(1,id)
        var rs = prepStatement.executeQuery()
        while(rs.next()){
            valeur=Valeur(
                rs.getInt("id"),
                rs.getInt("valeur1"),
                rs.getInt("valeur2")
            )
        }
        return valeur
    }
}