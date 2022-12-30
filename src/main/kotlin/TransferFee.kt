package ru.netoligy


class TransferFee {

    companion object {

        const val MAXAMOUNT_TYPE_1 = 75_000_000
        const val FIXCOMMISSION_TYPE_1 = 20_000
        const val COMMISSIONPERCENTAGE_TYPE_1 = 0.06
        const val FIXCOMMISSION_TYPE_3 = 35_000
        const val COMMISSIONPERCENTAGE_TYPE_3 = 0.0075
        const val COMMISSIONPERCENTAGE_TYPE_5 = 0
        const val RUB_PER_KOP = 1000
        const val MaxTransferinDay = 150_000_000
        const val MaxTransferinMonth = 600_000_000

        private fun rubToKop(amount: Long): Long {
            return amount * RUB_PER_KOP
        }

        private fun kopToRub(amount: Long): Long {
            return amount / RUB_PER_KOP
        }

        private fun calc(
            type: Enum<TypeCart>, amountOfPreviousPurchases: Int,
            amountTransfer: Int,
        ): Long {

            return when (type) {
                TypeCart.MasterCard, TypeCart.Maestro -> {
                    if ((rubToKop(amountOfPreviousPurchases.toLong()) + rubToKop((amountTransfer).toLong())
                                in (1..MAXAMOUNT_TYPE_1))
                    ) return 0
                    else return kopToRub((FIXCOMMISSION_TYPE_1 +
                            (COMMISSIONPERCENTAGE_TYPE_1 * rubToKop(amountTransfer.toLong()))).toLong())
                }
                TypeCart.VISA, TypeCart.Мир -> {
                    if (COMMISSIONPERCENTAGE_TYPE_3 * rubToKop(amountTransfer.toLong()) > FIXCOMMISSION_TYPE_1)
                        return kopToRub((COMMISSIONPERCENTAGE_TYPE_3 *
                                rubToKop(amountTransfer.toLong())).toLong())
                    else return kopToRub(FIXCOMMISSION_TYPE_3.toLong())
                }
                TypeCart.VK_Pay -> {
                    return kopToRub(COMMISSIONPERCENTAGE_TYPE_5 * rubToKop(amountTransfer.toLong()))
                }
                else -> {
                    return 0
                }
            }

        }


        fun start() {
            println("Введите  сумму перевода в руб.")
            val input = readln().toInt()
            val com = calc(TypeCart.VK_Pay, 10000, input)

            println("Комиссия = $com руб.")
        }


    }
}
