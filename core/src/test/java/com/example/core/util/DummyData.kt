package com.example.core.util

import com.example.core.data.source.local.entity.MediaEntity
import com.example.core.data.source.remote.model.MediasQuery
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaFormat
import com.example.core.data.source.remote.model.type.MediaFormat as RequestMediaFormat

object DummyData {
    const val BASE_TEST_PORT = 9900
    fun getMediaFormat() = MediaFormat.TV
    fun getKeyword() = "One Piece"
    fun getMediaId() = 20
    fun getMediasBody() =
        """{"data":{"Page":{"__typename":"Page","media":[{"__typename":"Media","id":1,"title":{"__typename":"MediaTitle","romaji":"Cowboy Bebop"},"coverImage":{"__typename":"MediaCoverImage","large":"https:\/\/s4.anilist.co\/file\/anilistcdn\/media\/anime\/cover\/medium\/bx1-CXtrrkMpJ8Zq.png"},"format":"TV","episodes":26,"description":"Enter a world in the distant future, where Bounty Hunters roam the solar system. Spike and Jet, bounty hunting partners, set out on journeys in an ever struggling effort to win bounty rewards to survive.<br><br> While traveling, they meet up with other very interesting people. Could Faye, the beautiful and ridiculously poor gambler, Edward, the computer genius, and Ein, the engineered dog be a good addition to the group?","bannerImage":"https:\/\/s4.anilist.co\/file\/anilistcdn\/media\/anime\/banner\/1-T3PJUjFJyRwg.jpg","duration":24,"averageScore":86,"favourites":13604}, {"__typename":"Media","id":20,"title":{"__typename":"MediaTitle","romaji":"Naruto"},"coverImage":{"__typename":"MediaCoverImage","large":"https:\/\/s4.anilist.co\/file\/anilistcdn\/media\/anime\/cover\/medium\/bx20-E3YH5W6sz6H7.jpg"},"format":"TV","episodes":220,"description":"Naruto Uzumaki, a hyperactive and knuckle-headed ninja, lives in Konohagakure, the Hidden Leaf village. Moments prior to his birth, a huge demon known as the Kyuubi, the Nine-tailed Fox, attacked Konohagakure and wreaked havoc. In order to put an end to the Kyuubi's rampage, the leader of the village, the 4th Hokage, sacrificed his life and sealed the monstrous beast inside the newborn Naruto. <br><br> Shunned because of the presence of the Kyuubi inside him, Naruto struggles to find his place in the village. He strives to become the Hokage of Konohagakure, and he meets many friends and foes along the way. <br><br> [Written by MAL Rewrite]","bannerImage":"https:\/\/s4.anilist.co\/file\/anilistcdn\/media\/anime\/banner\/20-HHxhPj5JD13a.jpg","duration":23,"averageScore":79,"favourites":18226}, {"__typename":"Media","id":1735,"title":{"__typename":"MediaTitle","romaji":"Naruto: Shippuuden"},"coverImage":{"__typename":"MediaCoverImage","large":"https:\/\/s4.anilist.co\/file\/anilistcdn\/media\/anime\/cover\/medium\/bx1735-80JNLAlnxrKj.png"},"format":"TV","episodes":500,"description":"Naruto: Shippuuden is the continuation of the original animated TV series Naruto. The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as he has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki. <br><br> (Source: Anime News Network)","bannerImage":"https:\/\/s4.anilist.co\/file\/anilistcdn\/media\/anime\/banner\/1735.jpg","duration":23,"averageScore":81,"favourites":21999}, {"__typename":"Media","id":4224,"title":{"__typename":"MediaTitle","romaji":"Toradora!"},"coverImage":{"__typename":"MediaCoverImage","large":"https:\/\/s4.anilist.co\/file\/anilistcdn\/media\/anime\/cover\/medium\/bx4224-3Bh0rm99N6Vl.jpg"},"format":"TV","episodes":25,"description":"Ryuuji Takasu is a gentle high school student with a love for housework; but in contrast to his kind nature, he has an intimidating face that often gets him labeled as a delinquent. On the other hand is Taiga Aisaka, a small, doll-like student, who is anything but a cute and fragile girl. Equipped with a wooden katana and feisty personality, Taiga is known throughout the school as the \"Palmtop Tiger.\"<br><br> One day, an embarrassing mistake causes the two students to cross paths. Ryuuji discovers that Taiga actually has a sweet side: she has a crush on the popular vice president, Yuusaku Kitamura, who happens to be his best friend. But things only get crazier when Ryuuji reveals that he has a crush on Minori Kushieda\u2014Taiga's best friend!<br><br><i>Toradora!<\/i> is a romantic comedy that follows this odd duo as they embark on a quest to help each other with their respective crushes, forming an unlikely alliance in the process.","bannerImage":"https:\/\/s4.anilist.co\/file\/anilistcdn\/media\/anime\/banner\/4224-iPUOHdMde27j.jpg","duration":24,"averageScore":80,"favourites":11738}]}}}"""

    fun getMedia(): Media {
        return Media(
            id = 20,
            title = "Naruto",
            imageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx20-E3YH5W6sz6H7.jpg",
            format = MediaFormat.TV,
            episodes = 220,
            description = "Naruto Uzumaki, a hyperactive and knuckle-headed ninja, lives in Konohagakure, the Hidden Leaf village. Moments prior to his birth, a huge demon known as the Kyuubi, the Nine-tailed Fox, attacked Konohagakure and wreaked havoc. In order to put an end to the Kyuubi's rampage, the leader of the village, the 4th Hokage, sacrificed his life and sealed the monstrous beast inside the newborn Naruto. <br><br> Shunned because of the presence of the Kyuubi inside him, Naruto struggles to find his place in the village. He strives to become the Hokage of Konohagakure, and he meets many friends and foes along the way. <br><br> [Written by MAL Rewrite]",
            bannerImageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/20-HHxhPj5JD13a.jpg",
            duration = 23,
            averageScore = 79,
            favorites = 18226,
            isFavorite = true
        )
    }

    fun getMedias(isFavorite: Boolean = true): List<Media> {
        return listOf(
            Media(
                id = 1,
                title = "Cowboy Bebop",
                imageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx1-CXtrrkMpJ8Zq.png",
                format = MediaFormat.TV,
                episodes = 26,
                description = "Enter a world in the distant future, where Bounty Hunters roam the solar system. Spike and Jet, bounty hunting partners, set out on journeys in an ever struggling effort to win bounty rewards to survive.<br><br> While traveling, they meet up with other very interesting people. Could Faye, the beautiful and ridiculously poor gambler, Edward, the computer genius, and Ein, the engineered dog be a good addition to the group?",
                bannerImageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/1-T3PJUjFJyRwg.jpg",
                duration = 24,
                averageScore = 86,
                favorites = 13604,
                isFavorite = isFavorite
            ),
            Media(
                id = 20,
                title = "Naruto",
                imageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx20-E3YH5W6sz6H7.jpg",
                format = MediaFormat.TV,
                episodes = 220,
                description = "Naruto Uzumaki, a hyperactive and knuckle-headed ninja, lives in Konohagakure, the Hidden Leaf village. Moments prior to his birth, a huge demon known as the Kyuubi, the Nine-tailed Fox, attacked Konohagakure and wreaked havoc. In order to put an end to the Kyuubi's rampage, the leader of the village, the 4th Hokage, sacrificed his life and sealed the monstrous beast inside the newborn Naruto. <br><br> Shunned because of the presence of the Kyuubi inside him, Naruto struggles to find his place in the village. He strives to become the Hokage of Konohagakure, and he meets many friends and foes along the way. <br><br> [Written by MAL Rewrite]",
                bannerImageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/20-HHxhPj5JD13a.jpg",
                duration = 23,
                averageScore = 79,
                favorites = 18226,
                isFavorite = isFavorite
            ),
            Media(
                id = 1735,
                title = "Naruto: Shippuuden",
                imageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx1735-80JNLAlnxrKj.png",
                format = MediaFormat.TV,
                episodes = 500,
                description = "Naruto: Shippuuden is the continuation of the original animated TV series Naruto. The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as he has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki. <br><br> (Source: Anime News Network)",
                bannerImageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/1735.jpg",
                duration = 23,
                averageScore = 81,
                favorites = 21999,
                isFavorite = isFavorite
            ),
            Media(
                id = 4224,
                title = "Toradora!",
                imageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx4224-3Bh0rm99N6Vl.jpg",
                format = MediaFormat.TV,
                episodes = 25,
                description = "Ryuuji Takasu is a gentle high school student with a love for housework; but in contrast to his kind nature, he has an intimidating face that often gets him labeled as a delinquent. On the other hand is Taiga Aisaka, a small, doll-like student, who is anything but a cute and fragile girl. Equipped with a wooden katana and feisty personality, Taiga is known throughout the school as the \"Palmtop Tiger.\"<br><br> One day, an embarrassing mistake causes the two students to cross paths. Ryuuji discovers that Taiga actually has a sweet side: she has a crush on the popular vice president, Yuusaku Kitamura, who happens to be his best friend. But things only get crazier when Ryuuji reveals that he has a crush on Minori Kushieda—Taiga's best friend!<br><br><i>Toradora!</i> is a romantic comedy that follows this odd duo as they embark on a quest to help each other with their respective crushes, forming an unlikely alliance in the process.",
                bannerImageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/4224-iPUOHdMde27j.jpg",
                duration = 24,
                averageScore = 80,
                favorites = 11738,
                isFavorite = isFavorite
            )
        )
    }

    fun getMediaEntity(): MediaEntity {
        return MediaEntity(
            id = 20,
            title = "Naruto",
            imageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx20-E3YH5W6sz6H7.jpg",
            format = MediaFormat.TV,
            episodes = 220,
            description = "Naruto Uzumaki, a hyperactive and knuckle-headed ninja, lives in Konohagakure, the Hidden Leaf village. Moments prior to his birth, a huge demon known as the Kyuubi, the Nine-tailed Fox, attacked Konohagakure and wreaked havoc. In order to put an end to the Kyuubi's rampage, the leader of the village, the 4th Hokage, sacrificed his life and sealed the monstrous beast inside the newborn Naruto. <br><br> Shunned because of the presence of the Kyuubi inside him, Naruto struggles to find his place in the village. He strives to become the Hokage of Konohagakure, and he meets many friends and foes along the way. <br><br> [Written by MAL Rewrite]",
            bannerImageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/20-HHxhPj5JD13a.jpg",
            duration = 23,
            averageScore = 79,
            favorites = 18226,
            isFavorite = true
        )
    }

    fun getMediaEntities(isFavorite: Boolean = true): List<MediaEntity> {
        return listOf(
            MediaEntity(
                id = 1,
                title = "Cowboy Bebop",
                imageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx1-CXtrrkMpJ8Zq.png",
                format = MediaFormat.TV,
                episodes = 26,
                description = "Enter a world in the distant future, where Bounty Hunters roam the solar system. Spike and Jet, bounty hunting partners, set out on journeys in an ever struggling effort to win bounty rewards to survive.<br><br> While traveling, they meet up with other very interesting people. Could Faye, the beautiful and ridiculously poor gambler, Edward, the computer genius, and Ein, the engineered dog be a good addition to the group?",
                bannerImageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/1-T3PJUjFJyRwg.jpg",
                duration = 24,
                averageScore = 86,
                favorites = 13604,
                isFavorite = isFavorite
            ),
            MediaEntity(
                id = 20,
                title = "Naruto",
                imageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx20-E3YH5W6sz6H7.jpg",
                format = MediaFormat.TV,
                episodes = 220,
                description = "Naruto Uzumaki, a hyperactive and knuckle-headed ninja, lives in Konohagakure, the Hidden Leaf village. Moments prior to his birth, a huge demon known as the Kyuubi, the Nine-tailed Fox, attacked Konohagakure and wreaked havoc. In order to put an end to the Kyuubi's rampage, the leader of the village, the 4th Hokage, sacrificed his life and sealed the monstrous beast inside the newborn Naruto. <br><br> Shunned because of the presence of the Kyuubi inside him, Naruto struggles to find his place in the village. He strives to become the Hokage of Konohagakure, and he meets many friends and foes along the way. <br><br> [Written by MAL Rewrite]",
                bannerImageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/20-HHxhPj5JD13a.jpg",
                duration = 23,
                averageScore = 79,
                favorites = 18226,
                isFavorite = isFavorite
            ),
            MediaEntity(
                id = 1735,
                title = "Naruto: Shippuuden",
                imageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx1735-80JNLAlnxrKj.png",
                format = MediaFormat.TV,
                episodes = 500,
                description = "Naruto: Shippuuden is the continuation of the original animated TV series Naruto. The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as he has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki. <br><br> (Source: Anime News Network)",
                bannerImageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/1735.jpg",
                duration = 23,
                averageScore = 81,
                favorites = 21999,
                isFavorite = isFavorite
            ),
            MediaEntity(
                id = 4224,
                title = "Toradora!",
                imageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx4224-3Bh0rm99N6Vl.jpg",
                format = MediaFormat.TV,
                episodes = 25,
                description = "Ryuuji Takasu is a gentle high school student with a love for housework; but in contrast to his kind nature, he has an intimidating face that often gets him labeled as a delinquent. On the other hand is Taiga Aisaka, a small, doll-like student, who is anything but a cute and fragile girl. Equipped with a wooden katana and feisty personality, Taiga is known throughout the school as the \"Palmtop Tiger.\"<br><br> One day, an embarrassing mistake causes the two students to cross paths. Ryuuji discovers that Taiga actually has a sweet side: she has a crush on the popular vice president, Yuusaku Kitamura, who happens to be his best friend. But things only get crazier when Ryuuji reveals that he has a crush on Minori Kushieda—Taiga's best friend!<br><br><i>Toradora!</i> is a romantic comedy that follows this odd duo as they embark on a quest to help each other with their respective crushes, forming an unlikely alliance in the process.",
                bannerImageUrl = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/4224-iPUOHdMde27j.jpg",
                duration = 24,
                averageScore = 80,
                favorites = 11738,
                isFavorite = isFavorite
            )
        )
    }

    fun getMediumList(): List<MediasQuery.Medium> {
        return listOf(
            MediasQuery.Medium(
                id = 1,
                title = MediasQuery.Title(romaji = "Cowboy Bebop"),
                coverImage = MediasQuery.CoverImage(large = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx1-CXtrrkMpJ8Zq.png"),
                format = RequestMediaFormat.TV,
                episodes = 26,
                description = "Enter a world in the distant future, where Bounty Hunters roam the solar system. Spike and Jet, bounty hunting partners, set out on journeys in an ever struggling effort to win bounty rewards to survive.<br><br> While traveling, they meet up with other very interesting people. Could Faye, the beautiful and ridiculously poor gambler, Edward, the computer genius, and Ein, the engineered dog be a good addition to the group?",
                bannerImage = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/1-T3PJUjFJyRwg.jpg",
                duration = 24,
                averageScore = 86,
                favourites = 13604
            ),
            MediasQuery.Medium(
                id = 20,
                title = MediasQuery.Title(romaji = "Naruto"),
                coverImage = MediasQuery.CoverImage(large = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx20-E3YH5W6sz6H7.jpg"),
                format = RequestMediaFormat.TV,
                episodes = 220,
                description = "Naruto Uzumaki, a hyperactive and knuckle-headed ninja, lives in Konohagakure, the Hidden Leaf village. Moments prior to his birth, a huge demon known as the Kyuubi, the Nine-tailed Fox, attacked Konohagakure and wreaked havoc. In order to put an end to the Kyuubi's rampage, the leader of the village, the 4th Hokage, sacrificed his life and sealed the monstrous beast inside the newborn Naruto. <br><br> Shunned because of the presence of the Kyuubi inside him, Naruto struggles to find his place in the village. He strives to become the Hokage of Konohagakure, and he meets many friends and foes along the way. <br><br> [Written by MAL Rewrite]",
                bannerImage = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/20-HHxhPj5JD13a.jpg",
                duration = 23,
                averageScore = 79,
                favourites = 18226
            ),
            MediasQuery.Medium(
                id = 1735,
                title = MediasQuery.Title(romaji = "Naruto: Shippuuden"),
                coverImage = MediasQuery.CoverImage(large = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx1735-80JNLAlnxrKj.png"),
                format = RequestMediaFormat.TV,
                episodes = 500,
                description = "Naruto: Shippuuden is the continuation of the original animated TV series Naruto. The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as he has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki. <br><br> (Source: Anime News Network)",
                bannerImage = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/1735.jpg",
                duration = 23,
                averageScore = 81,
                favourites = 21999
            ),
            MediasQuery.Medium(
                id = 4224,
                title = MediasQuery.Title(romaji = "Toradora!"),
                coverImage = MediasQuery.CoverImage(large = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx4224-3Bh0rm99N6Vl.jpg"),
                format = RequestMediaFormat.TV,
                episodes = 25,
                description = "Ryuuji Takasu is a gentle high school student with a love for housework; but in contrast to his kind nature, he has an intimidating face that often gets him labeled as a delinquent. On the other hand is Taiga Aisaka, a small, doll-like student, who is anything but a cute and fragile girl. Equipped with a wooden katana and feisty personality, Taiga is known throughout the school as the \"Palmtop Tiger.\"<br><br> One day, an embarrassing mistake causes the two students to cross paths. Ryuuji discovers that Taiga actually has a sweet side: she has a crush on the popular vice president, Yuusaku Kitamura, who happens to be his best friend. But things only get crazier when Ryuuji reveals that he has a crush on Minori Kushieda—Taiga's best friend!<br><br><i>Toradora!</i> is a romantic comedy that follows this odd duo as they embark on a quest to help each other with their respective crushes, forming an unlikely alliance in the process.",
                bannerImage = "https://s4.anilist.co/file/anilistcdn/media/anime/banner/4224-iPUOHdMde27j.jpg",
                duration = 24,
                averageScore = 80,
                favourites = 11738
            )
        )
    }
}