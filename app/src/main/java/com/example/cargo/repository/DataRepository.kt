package com.example.cargo.repository

import com.example.cargo.data.Query
import com.example.cargo.data.QueryComment
import com.example.cargo.utils.getRandImage
import com.example.cargo.utils.rand
import javax.inject.Inject

class DataRepository @Inject constructor() {
    private fun getFakeQueryComment(
        name: String = "Akash Rana",
        ans: String = "Good Question.."
    ): QueryComment {
        return QueryComment(
            receiver = name,
            receiverAnswerTxt = ans,
            time = "${rand(45)} min ago "
        )
    }

    fun getStaticData(): List<Query> {
        return listOf(
            Query(
                sender = "Anuj Rai",
                id = 0,
                queryTxt = "Soulful fruit and nut Millet Muesli!",
                answer = rand(3),
                likes = rand(15),
                time = "${rand(45)} min ago ",
                queryComment = listOf(
                    getFakeQueryComment(ans = "Buy Soulful Fruit and Nut Millet Muesli 400g pack online at Rs 299 in India.")
                )
            ),
            Query(
                sender = "Abdul Ragib",
                queryTxt = "Can one photo change the minds of everyone who dare look at it?",
                answer = rand(3),
                id = 1,
                likes = rand(15),
                time = "${rand(45)} min ago ",
                queryComment = listOf(
                    getFakeQueryComment(
                        name = "Abhinash K Mohan",
                        ans = "If she were only eating 30 calories a day she’d be like this."
                    ),
                    getFakeQueryComment(),
                )
            ),
            Query(
                sender = "Abhinav Mishra ",
                queryTxt = "If you like historical architecture, Forge is a must-play.",
                id = 2,
                answer = rand(3),
                likes = rand(15),
                time = "${rand(45)} min ago ",
                queryComment = listOf(
                    getFakeQueryComment("Abhishek Kumar", "let me think"),
                    getFakeQueryComment()
                )
            ),
            Query(
                sender = "Rishabh Dang",
                queryTxt = "What has your ex taught you?",
                answer = rand(3),
                id = 3,
                likes = rand(15),
                queryImage = getRandImage(),
                time = "${rand(45)} min ago ",
                queryComment = listOf(
                    getFakeQueryComment(
                        "Abhishek Kushwaha",
                        "Good TIme Matters"
                    ),
                    getFakeQueryComment(
                        "Adesh Patel",
                        "He need not follow everything his ex said."
                    ), getFakeQueryComment()
                )
            ),
            Query(
                sender = "Abhishek Kumar",
                queryTxt = "What are some screenshots that can get 91828828.2828 views?",
                answer = rand(3),
                id = 4,
                likes = rand(15),
                time = "${rand(45)} min ago ",
                queryComment = listOf(
                    getFakeQueryComment(
                        "Pranam Patel",
                        "This is the reality of present day generations. Most of are sharing the facts without checking the authenticity. They just want to make it"
                    ),
                    getFakeQueryComment(
                        "Aditya Sahore",
                        "This is the reality of present day generations. Most of are sharing the facts without checking the authenticity. They just want to make it"
                    ), getFakeQueryComment(ans = "True Bro..")
                )
            ),
            Query(
                sender = "Akash",
                queryTxt = "Kissing Artificial Goodbye...Hello Naturally Better Life",
                answer = rand(3),
                id = 5,
                likes = rand(15),
                queryImage = getRandImage(),
                time = "${rand(45)} min ago ",
                queryComment = listOf(
                    getFakeQueryComment("Amarpreet Kaur", "Exactly true"),
                    getFakeQueryComment("Adesh Patel", "Well Said.."),
                    getFakeQueryComment(ans = "Good Thinking")
                )
            ),
            Query(
                sender = "Kotagiri Sai Charan",
                queryTxt = "Adding Health to your Life Naturally",
                answer = rand(3),
                likes = rand(15),
                id = 6,
                time = "${rand(45)} min ago ",
                queryComment = listOf(
                    getFakeQueryComment(
                        "Kashish Gupta",
                        "Yes ,Well we should live a Life in Natural way"
                    ),
                    getFakeQueryComment("Manveen Kaur", "Well Said Thanks")
                )
            ),
            Query(
                sender = "Ankit Kumar",
                queryTxt = "Amazing Facts About Mooring (Malunggay) You Never Heard Before",
                answer = rand(3),
                likes = rand(15),
                id = 7,
                queryImage = getRandImage(),
                time = "${rand(45)} min ago ",
                queryComment = listOf(
                    getFakeQueryComment(
                        "Kumar Shivam",
                        "Unknown facts of mooring…do support"
                    ),
                    getFakeQueryComment()
                )
            )
        )
    }
}