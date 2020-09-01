package br.com.awm.pantherproducts.part01_products.utils;

import br.com.awm.pantherproducts.part01_products.exceptions.UnexpectedErrorException;
import br.com.awm.pantherproducts.part01_products.request.CreateProductsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@Component
public class CheckSumMD5Utils {

    private CheckSumMD5Utils() {
    }

    public static String getChecksum(List<CreateProductsRequest> object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream    oos = null;
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(baos.toByteArray());
            String result = DatatypeConverter.printHexBinary(thedigest);
            baos.close();
            oos.close();
            return result;
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            throw new UnexpectedErrorException(e.getMessage());
        }
    }
}
