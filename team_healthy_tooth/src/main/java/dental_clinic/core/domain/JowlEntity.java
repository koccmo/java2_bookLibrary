package dental_clinic.core.domain;

import javax.persistence.*;

@Entity
@Table(name="jowl")
public class JowlEntity {

    @Id
    @Column(name="id")
    private Long id;

    @OneToOne
    @JoinColumn(name="patient_id", nullable = false)
    private PersonalData personalData;

    @Column(name="d18", nullable = false)
    private String d18;

    @Column(name="d17", nullable = false)
    private String d17;

    @Column(name="d16", nullable = false)
    private String d16;

    @Column(name="d15", nullable = false)
    private String d15;

    @Column(name="d14", nullable = false)
    private String d14;

    @Column(name="d13", nullable = false)
    private String d13;

    @Column(name="d12", nullable = false)
    private String d12;

    @Column(name="d11", nullable = false)
    private String d11;

    @Column(name="d21", nullable = false)
    private String d21;

    @Column(name="d22", nullable = false)
    private String d22;

    @Column(name="d23", nullable = false)
    private String d23;

    @Column(name="d24", nullable = false)
    private String d24;

    @Column(name="d25", nullable = false)
    private String d25;

    @Column(name="d26", nullable = false)
    private String d26;

    @Column(name="d27", nullable = false)
    private String d27;

    @Column(name="d28", nullable = false)
    private String d28;

    @Column(name="d55", nullable = false)
    private String d55;

    @Column(name="d54", nullable = false)
    private String d54;

    @Column(name="d53", nullable = false)
    private String d53;

    @Column(name="d52", nullable = false)
    private String d52;

    @Column(name="d51", nullable = false)
    private String d51;

    @Column(name="d61", nullable = false)
    private String d61;

    @Column(name="d62", nullable = false)
    private String d62;

    @Column(name="d63", nullable = false)
    private String d63;

    @Column(name="d64", nullable = false)
    private String d64;

    @Column(name="d65", nullable = false)
    private String d65;

    @Column(name="d48", nullable = false)
    private String d48;

    @Column(name="d47", nullable = false)
    private String d47;

    @Column(name="d46", nullable = false)
    private String d46;

    @Column(name="d45", nullable = false)
    private String d45;

    @Column(name="d44", nullable = false)
    private String d44;

    @Column(name="d43", nullable = false)
    private String d43;

    @Column(name="d42", nullable = false)
    private String d42;

    @Column(name="d41", nullable = false)
    private String d41;

    @Column(name="d31", nullable = false)
    private String d31;

    @Column(name="d32", nullable = false)
    private String d32;

    @Column(name="d33", nullable = false)
    private String d33;

    @Column(name="d34", nullable = false)
    private String d34;

    @Column(name="d35", nullable = false)
    private String d35;

    @Column(name="d36", nullable = false)
    private String d36;

    @Column(name="d37", nullable = false)
    private String d37;

    @Column(name="d38", nullable = false)
    private String d38;

    @Column(name="d85", nullable = false)
    private String d85;

    @Column(name="d84", nullable = false)
    private String d84;

    @Column(name="d83", nullable = false)
    private String d83;

    @Column(name="d82", nullable = false)
    private String d82;

    @Column(name="d81", nullable = false)
    private String d81;

    @Column(name="d71", nullable = false)
    private String d71;

    @Column(name="d72", nullable = false)
    private String d72;

    @Column(name="d73", nullable = false)
    private String d73;

    @Column(name="d74", nullable = false)
    private String d74;

    @Column(name="d75", nullable = false)
    private String d75;

    @Column(name="isMost", nullable = false)
    private boolean isMost;

    @Column(name="m01s")
    private Long m01s;

    @Column(name="m01e")
    private Long m01e;

    @Column(name="m02s")
    private Long m02s;

    @Column(name="m02e")
    private Long m02e;

    @Column(name="m03s")
    private Long m03s;

    @Column(name="m03e")
    private Long m03e;

    @Column(name="m04s")
    private Long m04s;

    @Column(name="m04e")
    private Long m04e;

    @Column(name="m05s")
    private Long m05s;

    @Column(name="m05e")
    private Long m05e;

    @Column(name="m06s")
    private Long m06s;

    @Column(name="m06e")
    private Long m06e;

    public JowlEntity() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public String getD18() {
        return d18;
    }

    public void setD18(String d18) {
        this.d18 = d18;
    }

    public String getD17() {
        return d17;
    }

    public void setD17(String d17) {
        this.d17 = d17;
    }

    public String getD16() {
        return d16;
    }

    public void setD16(String d16) {
        this.d16 = d16;
    }

    public String getD15() {
        return d15;
    }

    public void setD15(String d15) {
        this.d15 = d15;
    }

    public String getD14() {
        return d14;
    }

    public void setD14(String d14) {
        this.d14 = d14;
    }

    public String getD13() {
        return d13;
    }

    public void setD13(String d13) {
        this.d13 = d13;
    }

    public String getD12() {
        return d12;
    }

    public void setD12(String d12) {
        this.d12 = d12;
    }

    public String getD11() {
        return d11;
    }

    public void setD11(String d11) {
        this.d11 = d11;
    }

    public String getD21() {
        return d21;
    }

    public void setD21(String d21) {
        this.d21 = d21;
    }

    public String getD22() {
        return d22;
    }

    public void setD22(String d22) {
        this.d22 = d22;
    }

    public String getD23() {
        return d23;
    }

    public void setD23(String d23) {
        this.d23 = d23;
    }

    public String getD24() {
        return d24;
    }

    public void setD24(String d24) {
        this.d24 = d24;
    }

    public String getD25() {
        return d25;
    }

    public void setD25(String d25) {
        this.d25 = d25;
    }

    public String getD26() {
        return d26;
    }

    public void setD26(String d26) {
        this.d26 = d26;
    }

    public String getD27() {
        return d27;
    }

    public void setD27(String d27) {
        this.d27 = d27;
    }

    public String getD28() {
        return d28;
    }

    public void setD28(String d28) {
        this.d28 = d28;
    }

    public String getD55() {
        return d55;
    }

    public void setD55(String d55) {
        this.d55 = d55;
    }

    public String getD54() {
        return d54;
    }

    public void setD54(String d54) {
        this.d54 = d54;
    }

    public String getD53() {
        return d53;
    }

    public void setD53(String d53) {
        this.d53 = d53;
    }

    public String getD52() {
        return d52;
    }

    public void setD52(String d52) {
        this.d52 = d52;
    }

    public String getD51() {
        return d51;
    }

    public void setD51(String d51) {
        this.d51 = d51;
    }

    public String getD61() {
        return d61;
    }

    public void setD61(String d61) {
        this.d61 = d61;
    }

    public String getD62() {
        return d62;
    }

    public void setD62(String d62) {
        this.d62 = d62;
    }

    public String getD63() {
        return d63;
    }

    public void setD63(String d63) {
        this.d63 = d63;
    }

    public String getD64() {
        return d64;
    }

    public void setD64(String d64) {
        this.d64 = d64;
    }

    public String getD65() {
        return d65;
    }

    public void setD65(String d65) {
        this.d65 = d65;
    }

    public String getD48() {
        return d48;
    }

    public void setD48(String d48) {
        this.d48 = d48;
    }

    public String getD47() {
        return d47;
    }

    public void setD47(String d47) {
        this.d47 = d47;
    }

    public String getD46() {
        return d46;
    }

    public void setD46(String d46) {
        this.d46 = d46;
    }

    public String getD45() {
        return d45;
    }

    public void setD45(String d45) {
        this.d45 = d45;
    }

    public String getD44() {
        return d44;
    }

    public void setD44(String d44) {
        this.d44 = d44;
    }

    public String getD43() {
        return d43;
    }

    public void setD43(String d43) {
        this.d43 = d43;
    }

    public String getD42() {
        return d42;
    }

    public void setD42(String d42) {
        this.d42 = d42;
    }

    public String getD41() {
        return d41;
    }

    public void setD41(String d41) {
        this.d41 = d41;
    }

    public String getD31() {
        return d31;
    }

    public void setD31(String d31) {
        this.d31 = d31;
    }

    public String getD32() {
        return d32;
    }

    public void setD32(String d32) {
        this.d32 = d32;
    }

    public String getD33() {
        return d33;
    }

    public void setD33(String d33) {
        this.d33 = d33;
    }

    public String getD34() {
        return d34;
    }

    public void setD34(String d34) {
        this.d34 = d34;
    }

    public String getD35() {
        return d35;
    }

    public void setD35(String d35) {
        this.d35 = d35;
    }

    public String getD36() {
        return d36;
    }

    public void setD36(String d36) {
        this.d36 = d36;
    }

    public String getD37() {
        return d37;
    }

    public void setD37(String d37) {
        this.d37 = d37;
    }

    public String getD38() {
        return d38;
    }

    public void setD38(String d38) {
        this.d38 = d38;
    }

    public String getD85() {
        return d85;
    }

    public void setD85(String d85) {
        this.d85 = d85;
    }

    public String getD84() {
        return d84;
    }

    public void setD84(String d84) {
        this.d84 = d84;
    }

    public String getD83() {
        return d83;
    }

    public void setD83(String d83) {
        this.d83 = d83;
    }

    public String getD82() {
        return d82;
    }

    public void setD82(String d82) {
        this.d82 = d82;
    }

    public String getD81() {
        return d81;
    }

    public void setD81(String d81) {
        this.d81 = d81;
    }

    public String getD71() {
        return d71;
    }

    public void setD71(String d71) {
        this.d71 = d71;
    }

    public String getD72() {
        return d72;
    }

    public void setD72(String d72) {
        this.d72 = d72;
    }

    public String getD73() {
        return d73;
    }

    public void setD73(String d73) {
        this.d73 = d73;
    }

    public String getD74() {
        return d74;
    }

    public void setD74(String d74) {
        this.d74 = d74;
    }

    public String getD75() {
        return d75;
    }

    public void setD75(String d75) {
        this.d75 = d75;
    }

    public boolean isMost() {
        return isMost;
    }

    public void setMost(boolean most) {
        isMost = most;
    }

    public Long getM01s() {
        return m01s;
    }

    public void setM01s(Long m01s) {
        this.m01s = m01s;
    }

    public Long getM01e() {
        return m01e;
    }

    public void setM01e(Long m01e) {
        this.m01e = m01e;
    }

    public Long getM02s() {
        return m02s;
    }

    public void setM02s(Long m02s) {
        this.m02s = m02s;
    }

    public Long getM02e() {
        return m02e;
    }

    public void setM02e(Long m02e) {
        this.m02e = m02e;
    }

    public Long getM03s() {
        return m03s;
    }

    public void setM03s(Long m03s) {
        this.m03s = m03s;
    }

    public Long getM03e() {
        return m03e;
    }

    public void setM03e(Long m03e) {
        this.m03e = m03e;
    }

    public Long getM04s() {
        return m04s;
    }

    public void setM04s(Long m04s) {
        this.m04s = m04s;
    }

    public Long getM04e() {
        return m04e;
    }

    public void setM04e(Long m04e) {
        this.m04e = m04e;
    }

    public Long getM05s() {
        return m05s;
    }

    public void setM05s(Long m05s) {
        this.m05s = m05s;
    }

    public Long getM05e() {
        return m05e;
    }

    public void setM05e(Long m05e) {
        this.m05e = m05e;
    }

    public Long getM06s() {
        return m06s;
    }

    public void setM06s(Long m06s) {
        this.m06s = m06s;
    }

    public Long getM06e() {
        return m06e;
    }

    public void setM06e(Long m06e) {
        this.m06e = m06e;
    }
}
