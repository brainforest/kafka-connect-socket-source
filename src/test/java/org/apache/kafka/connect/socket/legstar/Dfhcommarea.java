
package org.apache.kafka.connect.socket.legstar;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.legstar.coxb.CobolElement;
import com.legstar.coxb.CobolType;


/**
 * <p>Java class for Dfhcommarea complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Dfhcommarea">
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
 *         &lt;element name="dtar020Date">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;totalDigits value="7"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dtar020DeptNo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;totalDigits value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dtar020QtySold">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;totalDigits value="9"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dtar020SalePrice">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="11"/>
 *               &lt;fractionDigits value="2"/>
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
@XmlType(name = "Dfhcommarea", propOrder = {
    "dtar020KeycodeNo",
    "dtar020StoreNo",
    "dtar020Date",
    "dtar020DeptNo",
    "dtar020QtySold",
    "dtar020SalePrice"
})
public class Dfhcommarea
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    @CobolElement(cobolName = "DTAR020-KEYCODE-NO", type = CobolType.ALPHANUMERIC_ITEM, levelNumber = 3, picture = "X(08)", srceLine = 2)
    protected String dtar020KeycodeNo;
    @CobolElement(cobolName = "DTAR020-STORE-NO", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 3, isSigned = true, totalDigits = 3, picture = "S9(03)", usage = "PACKED-DECIMAL", srceLine = 3)
    protected short dtar020StoreNo;
    @CobolElement(cobolName = "DTAR020-DATE", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 3, isSigned = true, totalDigits = 7, picture = "S9(07)", usage = "PACKED-DECIMAL", srceLine = 4)
    protected int dtar020Date;
    @CobolElement(cobolName = "DTAR020-DEPT-NO", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 3, isSigned = true, totalDigits = 3, picture = "S9(03)", usage = "PACKED-DECIMAL", srceLine = 5)
    protected short dtar020DeptNo;
    @CobolElement(cobolName = "DTAR020-QTY-SOLD", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 3, isSigned = true, totalDigits = 9, picture = "S9(9)", usage = "PACKED-DECIMAL", srceLine = 6)
    protected int dtar020QtySold;
    @XmlElement(required = true)
    @CobolElement(cobolName = "DTAR020-SALE-PRICE", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 3, isSigned = true, totalDigits = 11, fractionDigits = 2, picture = "S9(9)V99", usage = "PACKED-DECIMAL", srceLine = 7)
    protected BigDecimal dtar020SalePrice;

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

    /**
     * Gets the value of the dtar020Date property.
     * 
     */
    public int getDtar020Date() {
        return dtar020Date;
    }

    /**
     * Sets the value of the dtar020Date property.
     * 
     */
    public void setDtar020Date(int value) {
        this.dtar020Date = value;
    }

    public boolean isSetDtar020Date() {
        return true;
    }

    /**
     * Gets the value of the dtar020DeptNo property.
     * 
     */
    public short getDtar020DeptNo() {
        return dtar020DeptNo;
    }

    /**
     * Sets the value of the dtar020DeptNo property.
     * 
     */
    public void setDtar020DeptNo(short value) {
        this.dtar020DeptNo = value;
    }

    public boolean isSetDtar020DeptNo() {
        return true;
    }

    /**
     * Gets the value of the dtar020QtySold property.
     * 
     */
    public int getDtar020QtySold() {
        return dtar020QtySold;
    }

    /**
     * Sets the value of the dtar020QtySold property.
     * 
     */
    public void setDtar020QtySold(int value) {
        this.dtar020QtySold = value;
    }

    public boolean isSetDtar020QtySold() {
        return true;
    }

    /**
     * Gets the value of the dtar020SalePrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDtar020SalePrice() {
        return dtar020SalePrice;
    }

    /**
     * Sets the value of the dtar020SalePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDtar020SalePrice(BigDecimal value) {
        this.dtar020SalePrice = value;
    }

    public boolean isSetDtar020SalePrice() {
        return (this.dtar020SalePrice!= null);
    }

    @Override
    public String toString() {
        return "Dfhcommarea{" +
                "dtar020KeycodeNo='" + dtar020KeycodeNo + '\'' +
                ", dtar020StoreNo=" + dtar020StoreNo +
                ", dtar020Date=" + dtar020Date +
                ", dtar020DeptNo=" + dtar020DeptNo +
                ", dtar020QtySold=" + dtar020QtySold +
                ", dtar020SalePrice=" + dtar020SalePrice +
                '}';
    }
}
