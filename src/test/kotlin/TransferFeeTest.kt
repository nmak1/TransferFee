import junit.framework.Assert.assertEquals
import org.junit.Test
import ru.netoligy.TransferFee
import ru.netoligy.TypeCart

class TransferFeeTest : TransferFee(){
    @Test
   fun testCalc_with_VK_pay() {

        val com = calc(TypeCart.VK_Pay,10000,10000)
        assertEquals(com,0)

    }
    @Test
    fun testCalc_With_MasterCard_pay() {

        val com = calc(TypeCart.MasterCard,100000,100000)
        assertEquals(com,6020)
        val com1 = calc(TypeCart.MasterCard,10000,10000)
        assertEquals(com1,0)
    }
    @Test
    fun testCalc_With_Visa_pay() {

        val com1 = calc(TypeCart.VISA,10000,10000)
        assertEquals(com1,75)
        val com = calc(TypeCart.VISA,1000,1000)
        assertEquals(com,35)
    }
}