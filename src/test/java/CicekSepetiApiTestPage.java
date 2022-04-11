import com.sun.org.glassfish.gmbal.Description;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class CicekSepetiApiTestPage extends MethodPage{

    //region Taksit seçeneği olan ürünlerin listelenmesi
    @Test(priority = 1)
    @Description("Taksit seçeneği olan ürünler listesi")
    public void getInstallmentTrueProducts (){
        getInstallmentTrue();
        logger.info("********* Taksit seçeneği olan ürünlerin name, installmentText, productGroupId ve installment bilgisi başarılı bir şekilde listelendi *********");
    }
    //endregion

    //region Taksit seçeneği olmayan ürünlerin listelenmesi
    @Test(priority = 2)
    @Description("Taksit seçeneği olmayan ürünler listesi")
    public void getInstallmentFalseProducts (){
        getInstallmentFalse();
        logger.info("********* Taksit seçeneği olmayan ürünlerin name, installmentText, productGroupId ve installment bilgisi başarılı bir şekilde listelendi *********");
    }
    //endregion

    //region Status code 500 kontrolü
    @Test(priority = 3)
    @Description("Status code 500 kontrolü")
    public void getInstallmentNullProducts (){
        getInstallmentNull();
        logger.info("********* Status code 500'ler listelendi *********");
    }
    //endregion
}
