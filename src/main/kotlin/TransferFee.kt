package ru.netoligy


class TransferFee {

    companion object {

        const val MAXAMOUNT_TYPE_1 = 75000
        const val FIXCOMMISSION_TYPE_1 = 20
        const val COMMISSIONPERCENTAGE_TYPE_1 = 0.06
        const val FIXCOMMISSION_TYPE_3 = 35
        const val COMMISSIONPERCENTAGE_TYPE_3 = 0.0075
        const val COMMISSIONPERCENTAGE_TYPE_5 = 0
        const val RUB_PER_KOP = 1000
        const val MaxTransferinDay = 150_000
        const val MaxTransferinMonth = 600_000

        private fun rubToKop(amount: Long): Long {
            return amount * RUB_PER_KOP
        }
        private fun kopToRub(amount: Long): Long {
            return amount / RUB_PER_KOP
        }

        private fun calc(
    type: Enum<TypeCart>, amountOfPreviousPurchases: Int,
    amountTransfer: Int) : Long {

          return  when (type){
               TypeCart.MasterCard,TypeCart.Maestro -> {
                if ((amountOfPreviousPurchases + amountTransfer) in (1..MAXAMOUNT_TYPE_1)) return 0
                else return ( FIXCOMMISSION_TYPE_1 + COMMISSIONPERCENTAGE_TYPE_1 * amountTransfer).toLong()
            }
               TypeCart.VISA,TypeCart.Мир->{
                   if (COMMISSIONPERCENTAGE_TYPE_3 * amountTransfer > FIXCOMMISSION_TYPE_1)
                       return (COMMISSIONPERCENTAGE_TYPE_3 * amountTransfer).toLong()
                   else return  FIXCOMMISSION_TYPE_3.toLong()
               }
                TypeCart.VK_Pay -> {
                    return (COMMISSIONPERCENTAGE_TYPE_5 * amountTransfer).toLong()
                }
              else -> {return 0}
          }

                }




        fun start() {
            println("Введите  сумму перевода в руб.")
            val input = readln().toInt()
            val com = calc(TypeCart.VK_Pay,100000,10000)

            println("Комиссия = $com руб.")
        }


    }
}
