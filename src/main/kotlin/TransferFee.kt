package ru.netoligy

class TransferFee {

    companion object {
        private const val COMMISSION_PERCENT = 0.75
        private const val MIN_COMMISSION_RUB = 35L
        private const val RUB_PER_KOP = 1000

        private fun rubToKop(amount: Long): Long {
            return amount * RUB_PER_KOP
        }
        private fun kopToRub(amount: Long): Long {
            return amount / RUB_PER_KOP
        }
        private fun calc(amount: Long): Long {
           var c = (COMMISSION_PERCENT* rubToKop(amount))/100
            if (c < MIN_COMMISSION_RUB) return MIN_COMMISSION_RUB else return kopToRub(c.toLong())
        }

        fun start() {
            println("Введите сумму перевода в руб.:")
            val input = readln().toLong()
            val com = calc(input)

            println("Комиссия = $com руб.")
        }


    }
}