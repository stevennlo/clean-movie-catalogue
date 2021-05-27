package com.example.moviecatalogue.util

import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaFormat

object DummyData {
    fun getMediaId() = 20
    fun getMediaFormat() = MediaFormat.TV
    fun getKeyword() = "One Piece"
    fun getMedias(): List<Media> {
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
                isFavorite = true
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
                isFavorite = true
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
                isFavorite = true
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
                isFavorite = true
            )
        )
    }

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
}