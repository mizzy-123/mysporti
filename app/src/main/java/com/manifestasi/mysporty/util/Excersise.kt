package com.manifestasi.mysporty.util

import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.data.model.DataExercise
import com.manifestasi.mysporty.data.model.Tutorial

object Excersise {
    val getData: List<DataExercise> = listOf(
        DataExercise(
            id = "jumping_jack",
            name = "Jumping Jack",
            start = "jumping_jack_start",
            start_state = "start",
            description = "Jumping jack adalah latihan kardio dasar yang melibatkan seluruh tubuh. Gerakan ini dimulai dengan berdiri tegak dengan tangan di samping tubuh, lalu melompat sambil merentangkan kaki ke samping dan mengangkat tangan ke atas kepala secara bersamaan. Setelah itu, kembali ke posisi awal. Latihan ini efektif untuk meningkatkan detak jantung, membakar kalori, dan menghangatkan tubuh sebelum latihan utama.",
            link_youtube = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CWpmIW6l-YA?si=URYW_jO7DKJDQnKv\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
            image = R.drawable.jumping_jack,
            start_position_image = R.drawable.jumping_jack_start,
            default_repetisi = 12,
            tutorialList = listOf(
                Tutorial(
                    name = "Posisi Awal",
                    description = "Berdiri tegak dengan kaki rapat dan tangan di sisi tubuh."
                ),
                Tutorial(
                    name = "Lompatan ke Atas",
                    description = "Lompat kecil sambil membuka kaki selebar bahu dan angkat tangan ke atas melewati kepala."
                ),
                Tutorial(
                    name = "Kembali ke Posisi Awal",
                    description = "Lompat kembali ke posisi awal dengan kaki rapat dan tangan kembali ke sisi tubuh."
                ),
                Tutorial(
                    name = "Ulangi Gerakan",
                    description = "Lanjutkan gerakan secara berulang dengan ritme yang stabil dan pernapasan teratur."
                )
            )
        ),
        DataExercise(
            id = "squat",
            name = "Squat",
            start = "squat_up",
            start_state = "up",
            description = "Squat adalah latihan kekuatan tubuh bagian bawah yang menargetkan otot paha, pinggul, dan bokong. Gerakannya dimulai dengan berdiri tegak, kaki selebar bahu, lalu menurunkan tubuh seolah-olah akan duduk di kursi sambil menjaga punggung tetap lurus dan lutut tidak melewati ujung jari kaki. Setelah posisi terendah tercapai, kembali berdiri. Squat membantu memperkuat otot inti dan menambah stabilitas.",
            link_youtube = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/YaXPRqUwItQ?si=sJbWrBxQeJIfEPYH\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
            image = R.drawable.squat,
            start_position_image = R.drawable.squat_start,
            default_repetisi = 20,
            tutorialList = listOf(
                Tutorial(
                    name = "Posisi Awal",
                    description = "Berdiri tegak dengan kaki sedikit lebih lebar dari bahu dan jari kaki mengarah sedikit ke luar. Tangan bisa di depan dada atau lurus ke depan."
                ),
                Tutorial(
                    name = "Turunkan Badan",
                    description = "Tekuk lutut dan dorong pinggul ke belakang seolah-olah akan duduk. Jaga punggung tetap lurus dan dada terbuka."
                ),
                Tutorial(
                    name = "Posisi Bawah",
                    description = "Turunkan badan hingga paha sejajar dengan lantai atau serendah yang nyaman, dengan lutut tidak melewati ujung jari kaki."
                ),
                Tutorial(
                    name = "Kembali Berdiri",
                    description = "Dorong tumit untuk kembali ke posisi berdiri, tetap jaga postur tubuh dan seimbangkan gerakan."
                ),
                Tutorial(
                    name = "Ulangi Gerakan",
                    description = "Lakukan gerakan squat secara berulang dengan kontrol dan napas yang stabil."
                )
            )
        ),
        DataExercise(
            id = "pushup",
            name = "Pushup",
            start = "pushup_up",
            start_state = "up",
            description = "Push-up adalah latihan kekuatan tubuh bagian atas yang melatih otot dada, bahu, trisep, dan otot inti. Dimulai dalam posisi plank dengan tangan di lantai sejajar bahu dan tubuh membentuk garis lurus dari kepala hingga kaki. Turunkan tubuh hingga dada hampir menyentuh lantai, lalu dorong kembali ke posisi awal. Push-up meningkatkan daya tahan otot dan stabilitas tubuh bagian atas.",
            link_youtube = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/mECzqUIDWfU?si=FUS_OmoLct7pHQUl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
            image = R.drawable.pushup,
            start_position_image = R.drawable.pushup_start,
            default_repetisi = 16,
            tutorialList = listOf(
                Tutorial(
                    name = "Push-up Up (Posisi Awal)",
                    description = "Mulai dari posisi atas dengan lengan lurus menopang tubuh. Tangan sejajar bahu, tubuh membentuk garis lurus dari kepala hingga tumit."
                ),
                Tutorial(
                    name = "Turunkan Badan",
                    description = "Tekuk siku secara perlahan dan turunkan badan menuju lantai. Jaga punggung dan pinggul tetap sejajar."
                ),
                Tutorial(
                    name = "Push-up Down (Posisi Bawah)",
                    description = "Berhenti saat dada hampir menyentuh lantai, siku membentuk sudut sekitar 90 derajat."
                ),
                Tutorial(
                    name = "Dorong ke Atas",
                    description = "Dorong tubuh kembali ke posisi atas (push-up up) dengan menekan telapak tangan ke lantai."
                ),
                Tutorial(
                    name = "Ulangi Gerakan",
                    description = "Lakukan gerakan push-up secara berulang dengan stabil dan kontrol penuh."
                )
            )
        ),
        DataExercise(
            id = "ab_crunch",
            name = "Abdominal Crunch",
            start = "ab_crunch_down",
            start_state = "down",
            description = "Abdominal crunch adalah latihan yang menargetkan otot perut bagian atas. Posisi awal adalah berbaring telentang dengan lutut ditekuk dan telapak kaki menyentuh lantai. Tangan dapat diletakkan di belakang kepala atau di dada. Angkat bagian atas tubuh ke arah lutut sambil mengepalkan otot perut, lalu kembali ke posisi semula. Crunch berguna untuk memperkuat dan membentuk otot perut.",
            link_youtube = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/_M2Etme-tfE?si=hUurPUpwLatcx1io\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
            image = R.drawable.ab_crunch,
            start_position_image = R.drawable.ab_crunch_start,
            default_repetisi = 16,
            tutorialList = listOf(
                Tutorial(
                    name = "Posisi Awal",
                    description = "Berbaring telentang di atas matras, lutut ditekuk dan telapak kaki menempel di lantai. Letakkan tangan di belakang kepala atau di dada."
                ),
                Tutorial(
                    name = "Kontraksi Otot Perut",
                    description = "Angkat kepala, leher, dan bahu dari lantai dengan menggunakan otot perut. Jaga punggung bagian bawah tetap menempel di lantai."
                ),
                Tutorial(
                    name = "Posisi Puncak",
                    description = "Berhenti sejenak saat otot perut terkontraksi maksimal. Pandangan mengarah ke langit-langit, jangan menarik leher dengan tangan."
                ),
                Tutorial(
                    name = "Turun Perlahan",
                    description = "Turunkan badan kembali ke posisi awal secara perlahan sambil tetap mengontrol gerakan dengan otot perut."
                ),
                Tutorial(
                    name = "Ulangi Gerakan",
                    description = "Lakukan gerakan crunch berulang kali dengan napas stabil. Buang napas saat naik, tarik napas saat turun."
                )
            )
        )
    )
}