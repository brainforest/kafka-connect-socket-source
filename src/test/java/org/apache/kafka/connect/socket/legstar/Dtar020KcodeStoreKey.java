
package org.apache.kafka.connect.socket.legstar;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.legstar.coxb.CobolElement;
import com.legstar.coxb.CobolType;


/**
 * <p>Java class for Dtar020KcodeStoreKey complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Dtar020KcodeStoreKey">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dtar020KeycodeNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dtar020StoreNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;totalDigits value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dtar020KcodeStoreKey", propOrder = {
    "dtar020KeycodeNo",
    "dtar020StoreNo"
})
public class Dtar020KcodeStoreKey
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    @CobolElement(cobolName = "DTAR020-KEYCODE-NO", type = CobolType.ALPHANUMERIC_ITEM, levelNumber = 5, picture = "X(08)", srceLine = 10)
    protected String dtar020KeycodeNo;
    @CobolElement(cobolName = "DTAR020-STORE-NO", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = true, totalDigits = 3, picture = "S9(03)", usage = "PACKED-DECIMAL", srceLine = 11)
    protected short dtar020StoreNo;

    /**
     * Gets the value of the dtar020KeycodeNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDtar020KeycodeNo() {
        return dtar020KeycodeNo;
    }

    /**
     * Sets the value of the dtar020KeycodeNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDtar020KeycodeNo(String value) {
        this.dtar020KeycodeNo = value;
    }

    public boolean isSetDtar020KeycodeNo() {
        return (this.dtar020KeycodeNo!= null);
    }

    /**
     * Gets the value of the dtar020StoreNo property.
     * 
     */
    public short getDtar020StoreNo() {
        return dtar020StoreNo;
    }

    /**
     * Sets the value of the dtar020StoreNo property.
     * 
     */
    public void setDtar020StoreNo(short value) {
        this.dtar020StoreNo = value;
    }

    public boolean isSetDtar020StoreNo() {
        return true;
    }

}
