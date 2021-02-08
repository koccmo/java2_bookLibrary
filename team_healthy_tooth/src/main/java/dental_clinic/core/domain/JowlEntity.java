package dental_clinic.core.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="jowl")
public class JowlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne
    @JoinColumn(name="patient_id", nullable = false)
    private PersonalData personalData;

    @Column(name="d18", nullable = false)
    private ToothStatus d18 = ToothStatus.HEALTHY;

    @Column(name="d17", nullable = false)
    private ToothStatus d17 = ToothStatus.HEALTHY;

    @Column(name="d16", nullable = false)
    private ToothStatus d16 = ToothStatus.HEALTHY;

    @Column(name="d15", nullable = false)
    private ToothStatus d15 = ToothStatus.HEALTHY;

    @Column(name="d14", nullable = false)
    private ToothStatus d14 = ToothStatus.HEALTHY;

    @Column(name="d13", nullable = false)
    private ToothStatus d13 = ToothStatus.HEALTHY;

    @Column(name="d12", nullable = false)
    private ToothStatus d12 = ToothStatus.HEALTHY;

    @Column(name="d11", nullable = false)
    private ToothStatus d11 = ToothStatus.HEALTHY;

    @Column(name="d21", nullable = false)
    private ToothStatus d21 = ToothStatus.HEALTHY;

    @Column(name="d22", nullable = false)
    private ToothStatus d22 = ToothStatus.HEALTHY;

    @Column(name="d23", nullable = false)
    private ToothStatus d23 = ToothStatus.HEALTHY;

    @Column(name="d24", nullable = false)
    private ToothStatus d24 = ToothStatus.HEALTHY;

    @Column(name="d25", nullable = false)
    private ToothStatus d25 = ToothStatus.HEALTHY;

    @Column(name="d26", nullable = false)
    private ToothStatus d26 = ToothStatus.HEALTHY;

    @Column(name="d27", nullable = false)
    private ToothStatus d27 = ToothStatus.HEALTHY;

    @Column(name="d28", nullable = false)
    private ToothStatus d28 = ToothStatus.HEALTHY;

    @Column(name="d55", nullable = false)
    private ToothStatus d55 = ToothStatus.HEALTHY;

    @Column(name="d54", nullable = false)
    private ToothStatus d54 = ToothStatus.HEALTHY;

    @Column(name="d53", nullable = false)
    private ToothStatus d53 = ToothStatus.HEALTHY;

    @Column(name="d52", nullable = false)
    private ToothStatus d52 = ToothStatus.HEALTHY;

    @Column(name="d51", nullable = false)
    private ToothStatus d51 = ToothStatus.HEALTHY;

    @Column(name="d61", nullable = false)
    private ToothStatus d61 = ToothStatus.HEALTHY;

    @Column(name="d62", nullable = false)
    private ToothStatus d62 = ToothStatus.HEALTHY;

    @Column(name="d63", nullable = false)
    private ToothStatus d63 = ToothStatus.HEALTHY;

    @Column(name="d64", nullable = false)
    private ToothStatus d64 = ToothStatus.HEALTHY;

    @Column(name="d65", nullable = false)
    private ToothStatus d65 = ToothStatus.HEALTHY;

    @Column(name="d48", nullable = false)
    private ToothStatus d48 = ToothStatus.HEALTHY;

    @Column(name="d47", nullable = false)
    private ToothStatus d47 = ToothStatus.HEALTHY;

    @Column(name="d46", nullable = false)
    private ToothStatus d46 = ToothStatus.HEALTHY;

    @Column(name="d45", nullable = false)
    private ToothStatus d45 = ToothStatus.HEALTHY;

    @Column(name="d44", nullable = false)
    private ToothStatus d44 = ToothStatus.HEALTHY;

    @Column(name="d43", nullable = false)
    private ToothStatus d43 = ToothStatus.HEALTHY;

    @Column(name="d42", nullable = false)
    private ToothStatus d42 = ToothStatus.HEALTHY;

    @Column(name="d41", nullable = false)
    private ToothStatus d41 = ToothStatus.HEALTHY;

    @Column(name="d31", nullable = false)
    private ToothStatus d31 = ToothStatus.HEALTHY;

    @Column(name="d32", nullable = false)
    private ToothStatus d32 = ToothStatus.HEALTHY;

    @Column(name="d33", nullable = false)
    private ToothStatus d33 = ToothStatus.HEALTHY;

    @Column(name="d34", nullable = false)
    private ToothStatus d34 = ToothStatus.HEALTHY;

    @Column(name="d35", nullable = false)
    private ToothStatus d35 = ToothStatus.HEALTHY;

    @Column(name="d36", nullable = false)
    private ToothStatus d36 = ToothStatus.HEALTHY;

    @Column(name="d37", nullable = false)
    private ToothStatus d37 = ToothStatus.HEALTHY;

    @Column(name="d38", nullable = false)
    private ToothStatus d38 = ToothStatus.HEALTHY;

    @Column(name="d85", nullable = false)
    private ToothStatus d85 = ToothStatus.HEALTHY;

    @Column(name="d84", nullable = false)
    private ToothStatus d84 = ToothStatus.HEALTHY;

    @Column(name="d83", nullable = false)
    private ToothStatus d83 = ToothStatus.HEALTHY;

    @Column(name="d82", nullable = false)
    private ToothStatus d82 = ToothStatus.HEALTHY;

    @Column(name="d81", nullable = false)
    private ToothStatus d81 = ToothStatus.HEALTHY;

    @Column(name="d71", nullable = false)
    private ToothStatus d71 = ToothStatus.HEALTHY;

    @Column(name="d72", nullable = false)
    private ToothStatus d72 = ToothStatus.HEALTHY;

    @Column(name="d73", nullable = false)
    private ToothStatus d73 = ToothStatus.HEALTHY;

    @Column(name="d74", nullable = false)
    private ToothStatus d74 = ToothStatus.HEALTHY;

    @Column(name="d75", nullable = false)
    private ToothStatus d75 = ToothStatus.HEALTHY;

    @Column(name="isMost", nullable = false)
    private boolean isMost = false;

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

    public JowlEntity(PersonalData personalData) {
        this.personalData = personalData;
    }

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

    public ToothStatus getD18() {
        return d18;
    }

    public void setD18(ToothStatus d18) {
        this.d18 = d18;
    }

    public ToothStatus getD17() {
        return d17;
    }

    public void setD17(ToothStatus d17) {
        this.d17 = d17;
    }

    public ToothStatus getD16() {
        return d16;
    }

    public void setD16(ToothStatus d16) {
        this.d16 = d16;
    }

    public ToothStatus getD15() {
        return d15;
    }

    public void setD15(ToothStatus d15) {
        this.d15 = d15;
    }

    public ToothStatus getD14() {
        return d14;
    }

    public void setD14(ToothStatus d14) {
        this.d14 = d14;
    }

    public ToothStatus getD13() {
        return d13;
    }

    public void setD13(ToothStatus d13) {
        this.d13 = d13;
    }

    public ToothStatus getD12() {
        return d12;
    }

    public void setD12(ToothStatus d12) {
        this.d12 = d12;
    }

    public ToothStatus getD11() {
        return d11;
    }

    public void setD11(ToothStatus d11) {
        this.d11 = d11;
    }

    public ToothStatus getD21() {
        return d21;
    }

    public void setD21(ToothStatus d21) {
        this.d21 = d21;
    }

    public ToothStatus getD22() {
        return d22;
    }

    public void setD22(ToothStatus d22) {
        this.d22 = d22;
    }

    public ToothStatus getD23() {
        return d23;
    }

    public void setD23(ToothStatus d23) {
        this.d23 = d23;
    }

    public ToothStatus getD24() {
        return d24;
    }

    public void setD24(ToothStatus d24) {
        this.d24 = d24;
    }

    public ToothStatus getD25() {
        return d25;
    }

    public void setD25(ToothStatus d25) {
        this.d25 = d25;
    }

    public ToothStatus getD26() {
        return d26;
    }

    public void setD26(ToothStatus d26) {
        this.d26 = d26;
    }

    public ToothStatus getD27() {
        return d27;
    }

    public void setD27(ToothStatus d27) {
        this.d27 = d27;
    }

    public ToothStatus getD28() {
        return d28;
    }

    public void setD28(ToothStatus d28) {
        this.d28 = d28;
    }

    public ToothStatus getD55() {
        return d55;
    }

    public void setD55(ToothStatus d55) {
        this.d55 = d55;
    }

    public ToothStatus getD54() {
        return d54;
    }

    public void setD54(ToothStatus d54) {
        this.d54 = d54;
    }

    public ToothStatus getD53() {
        return d53;
    }

    public void setD53(ToothStatus d53) {
        this.d53 = d53;
    }

    public ToothStatus getD52() {
        return d52;
    }

    public void setD52(ToothStatus d52) {
        this.d52 = d52;
    }

    public ToothStatus getD51() {
        return d51;
    }

    public void setD51(ToothStatus d51) {
        this.d51 = d51;
    }

    public ToothStatus getD61() {
        return d61;
    }

    public void setD61(ToothStatus d61) {
        this.d61 = d61;
    }

    public ToothStatus getD62() {
        return d62;
    }

    public void setD62(ToothStatus d62) {
        this.d62 = d62;
    }

    public ToothStatus getD63() {
        return d63;
    }

    public void setD63(ToothStatus d63) {
        this.d63 = d63;
    }

    public ToothStatus getD64() {
        return d64;
    }

    public void setD64(ToothStatus d64) {
        this.d64 = d64;
    }

    public ToothStatus getD65() {
        return d65;
    }

    public void setD65(ToothStatus d65) {
        this.d65 = d65;
    }

    public ToothStatus getD48() {
        return d48;
    }

    public void setD48(ToothStatus d48) {
        this.d48 = d48;
    }

    public ToothStatus getD47() {
        return d47;
    }

    public void setD47(ToothStatus d47) {
        this.d47 = d47;
    }

    public ToothStatus getD46() {
        return d46;
    }

    public void setD46(ToothStatus d46) {
        this.d46 = d46;
    }

    public ToothStatus getD45() {
        return d45;
    }

    public void setD45(ToothStatus d45) {
        this.d45 = d45;
    }

    public ToothStatus getD44() {
        return d44;
    }

    public void setD44(ToothStatus d44) {
        this.d44 = d44;
    }

    public ToothStatus getD43() {
        return d43;
    }

    public void setD43(ToothStatus d43) {
        this.d43 = d43;
    }

    public ToothStatus getD42() {
        return d42;
    }

    public void setD42(ToothStatus d42) {
        this.d42 = d42;
    }

    public ToothStatus getD41() {
        return d41;
    }

    public void setD41(ToothStatus d41) {
        this.d41 = d41;
    }

    public ToothStatus getD31() {
        return d31;
    }

    public void setD31(ToothStatus d31) {
        this.d31 = d31;
    }

    public ToothStatus getD32() {
        return d32;
    }

    public void setD32(ToothStatus d32) {
        this.d32 = d32;
    }

    public ToothStatus getD33() {
        return d33;
    }

    public void setD33(ToothStatus d33) {
        this.d33 = d33;
    }

    public ToothStatus getD34() {
        return d34;
    }

    public void setD34(ToothStatus d34) {
        this.d34 = d34;
    }

    public ToothStatus getD35() {
        return d35;
    }

    public void setD35(ToothStatus d35) {
        this.d35 = d35;
    }

    public ToothStatus getD36() {
        return d36;
    }

    public void setD36(ToothStatus d36) {
        this.d36 = d36;
    }

    public ToothStatus getD37() {
        return d37;
    }

    public void setD37(ToothStatus d37) {
        this.d37 = d37;
    }

    public ToothStatus getD38() {
        return d38;
    }

    public void setD38(ToothStatus d38) {
        this.d38 = d38;
    }

    public ToothStatus getD85() {
        return d85;
    }

    public void setD85(ToothStatus d85) {
        this.d85 = d85;
    }

    public ToothStatus getD84() {
        return d84;
    }

    public void setD84(ToothStatus d84) {
        this.d84 = d84;
    }

    public ToothStatus getD83() {
        return d83;
    }

    public void setD83(ToothStatus d83) {
        this.d83 = d83;
    }

    public ToothStatus getD82() {
        return d82;
    }

    public void setD82(ToothStatus d82) {
        this.d82 = d82;
    }

    public ToothStatus getD81() {
        return d81;
    }

    public void setD81(ToothStatus d81) {
        this.d81 = d81;
    }

    public ToothStatus getD71() {
        return d71;
    }

    public void setD71(ToothStatus d71) {
        this.d71 = d71;
    }

    public ToothStatus getD72() {
        return d72;
    }

    public void setD72(ToothStatus d72) {
        this.d72 = d72;
    }

    public ToothStatus getD73() {
        return d73;
    }

    public void setD73(ToothStatus d73) {
        this.d73 = d73;
    }

    public ToothStatus getD74() {
        return d74;
    }

    public void setD74(ToothStatus d74) {
        this.d74 = d74;
    }

    public ToothStatus getD75() {
        return d75;
    }

    public void setD75(ToothStatus d75) {
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

    private Map<Integer, List<ToothStatus>> getJowl(){
        Map <Integer, List<ToothStatus>> newJowl = new HashMap<>();
        for (int i = 18; i>10; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 21; i<29; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 55; i>50; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 61; i<66; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }

        for (int i = 48; i>40; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 31; i<39; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 85; i>80; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 71; i<76; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        return newJowl;
    }

    @Override
    public String toString() {
        return "Jowl:\n" +
                "\nd18: " + d18 +
                "\nd17: " + d17 +
                "\nd16: " + d16 +
                "\nd15: " + d15 +
                "\nd14: " + d14 +
                "\nd13: " + d13 +
                "\nd12: " + d12 +
                "\nd11: " + d11 +
                "\nd21: " + d21 +
                "\nd22: " + d22 +
                "\nd23: " + d23 +
                "\nd24: " + d24 +
                "\nd25: " + d25 +
                "\nd26: " + d26 +
                "\nd27: " + d27 +
                "\nd28: " + d28 +
                "\nd55: " + d55 +
                "\nd54: " + d54 +
                "\nd53: " + d53 +
                "\nd52: " + d52 +
                "\nd51: " + d51 +
                "\nd61: " + d61 +
                "\nd62: " + d62 +
                "\nd63: " + d63 +
                "\nd64: " + d64 +
                "\nd65: " + d65 +
                "\nd48: " + d48 +
                "\nd47: " + d47 +
                "\nd46: " + d46 +
                "\nd45: " + d45 +
                "\nd44: " + d44 +
                "\nd43: " + d43 +
                "\nd42: " + d42 +
                "\nd41: " + d41 +
                "\nd31: " + d31 +
                "\nd32: " + d32 +
                "\nd33: " + d33 +
                "\nd34: " + d34 +
                "\nd35: " + d35 +
                "\nd36: " + d36 +
                "\nd37: " + d37 +
                "\nd38: " + d38 +
                "\nd85: " + d85 +
                "\nd84: " + d84 +
                "\nd83: " + d83 +
                "\nd82: " + d82 +
                "\nd81: " + d81 +
                "\nd71: " + d71 +
                "\nd72: " + d72 +
                "\nd73: " + d73 +
                "\nd74: " + d74 +
                "\nd75: " + d75 +
                "\nisMost: " + isMost +
                "\nm01s: " + m01s +
                "\nm01e: " + m01e +
                "\nm02s: " + m02s +
                "\nm02e: " + m02e +
                "\nm03s: " + m03s +
                "\nm03e: " + m03e +
                "\nm04s: " + m04s +
                "\nm04e: " + m04e +
                "\nm05s: " + m05s +
                "\nm05e: " + m05e +
                "\nm06s: " + m06s +
                "\nm06e: " + m06e + "\n";
    }
}
