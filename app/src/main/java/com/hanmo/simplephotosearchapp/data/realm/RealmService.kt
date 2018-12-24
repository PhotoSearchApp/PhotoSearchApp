package com.hanmo.simplephotosearchapp.data.realm

import com.hanmo.simplephotosearchapp.data.model.Keyword
import io.realm.Realm
import io.realm.RealmResults

object RealmService {

    fun createKeywordList() {
        Realm.getDefaultInstance().use { realm ->
            val keywordObject = realm.where(Keyword::class.java).findFirst()

            if (keywordObject == null) {
                val keywordList = listOf(
                        "Apple", "Banana", "Cat",
                        "Dog", "Egg", "Fox", "Goat",
                        "Horse", "Icon", "Jamaica"
                )

                keywordList.forEachIndexed { index, keywordName ->
                    val keyword = Keyword()
                    keyword.apply {
                        id  = index
                        name = keywordName
                    }

                    realm.executeTransaction {
                        realm.copyToRealm(keyword)
                    }
                }
            }
        }
    }

    fun getKeywordList(): RealmResults<Keyword>? {
        Realm.getDefaultInstance().use { realm ->
            return realm.where(Keyword::class.java)?.findAll()
        }
    }

    fun getFirstKeyword(): String? {
        Realm.getDefaultInstance().use { realm ->
            return realm.where(Keyword::class.java)?.findAll()?.first()?.name
        }
    }
}