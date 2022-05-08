package com.example.myapp.data;
import android.net.Uri;

public class DataUser {
    public String API = "http://103.172.79.62:5000/";
    public  String fullNameStatic = "No Name 1234";

    public  String emailStatic;

    public  String passWordStatic;
    public  String phoneStatic = "No Phone";
    public  String byteArrayImageStatic = "";
    public  String dateOfBirthStatic;
    public  String sexStatic;
    public  String cityStatic;
    public  String townshipStatic;
    public  String wardStatic;
    public  String apartmentNumberStatic="";
    public  float moneyStatic;
    public  int accumulatedPointsStatic;
    public  int carepayactive;
    public  String setlock;
    public  String addcard;

    public String like;

    public  boolean carepay = false;
    public  String idcard;
    public  Uri facecheck = null;
    public  Uri paper1 = null;
    public  Uri paper2 = null;


    public  String TransferCarePayPhone;
    public  String TransferCarePayName;
    public  String TransferCarePayContent;
    public  String TransferCarePayAM;
    public  Integer TransferCarePayCHECK = 0;

    public  String TransferBankPhone;
    public  String TransferBankName;
    public  String TransferBankContent;
    public  String TransferBankAM;
    public  Integer TransferBankCHECK = 0;

    public  Integer CheckIsBank = 0;


    public String commentImageFace;
    public String commentTime;
    public String commentDate;
    public String commentFullName;
    public String commentImage;
    public String commentNumberLike;
    public String commentContent;

    public String commentIDPost;
    public String commentIDPeople;


    public Integer k;
    public Integer LIKEORDIS = 0;

    public String Location;

    public String HomeOneNameHospital;




    public String HomeOneNameUser;
    public String HomeOneGenderUser;
    public String HomeOneAgeUser;
    public String HomeOnePhoneUser;
    public String HomeOneAddressUser;

    public String HomeOneSpecHospital;

    public String HomeOneDate;
    public String HomeOneTime ="";
    public String HomeOneType ="";

    public String CheckTestAtHome;

    public String HomeTowAddress="";

    public String ResultXray;

    public String ServiceContent;
    public String ServiceMoney;
    public String ServiceImage;
    public String ServicehName;
    public String CheckCall;
    //image, name, hospital, address, phone, chungchi;
    public String DoctorSpecname;
    public String DoctorSpecimage;
    public String DoctorSpechopital;
    public String DoctorSpecaddress;
    public String DoctorSpecphone;
    public String DoctorSpecchungchi;


    public String getCardFronid;
    public String getCardFrongender;
    public String getCardFrondob;
    public String getCardFronprovince;
    public String getCardFrondistrict;
    public String getCardFronward;
    public String getCardFronaddress;
    public String getCardFronname;

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getSetlock() {
        return setlock;
    }

    public void setSetlock(String setlock) {
        this.setlock = setlock;
    }

    public String getGetCardFronname() {
        return getCardFronname;
    }

    public void setGetCardFronname(String getCardFronname) {
        this.getCardFronname = getCardFronname;
    }

    public String getGetCardFronid() {
        return getCardFronid;
    }

    public void setGetCardFronid(String getCardFronid) {
        this.getCardFronid = getCardFronid;
    }

    public String getGetCardFrongender() {
        return getCardFrongender;
    }

    public void setGetCardFrongender(String getCardFrongender) {
        this.getCardFrongender = getCardFrongender;
    }

    public String getGetCardFrondob() {
        return getCardFrondob;
    }

    public void setGetCardFrondob(String getCardFrondob) {
        this.getCardFrondob = getCardFrondob;
    }

    public String getGetCardFronprovince() {
        return getCardFronprovince;
    }

    public void setGetCardFronprovince(String getCardFronprovince) {
        this.getCardFronprovince = getCardFronprovince;
    }

    public String getGetCardFrondistrict() {
        return getCardFrondistrict;
    }

    public void setGetCardFrondistrict(String getCardFrondistrict) {
        this.getCardFrondistrict = getCardFrondistrict;
    }

    public String getGetCardFronward() {
        return getCardFronward;
    }

    public void setGetCardFronward(String getCardFronward) {
        this.getCardFronward = getCardFronward;
    }

    public String getGetCardFronaddress() {
        return getCardFronaddress;
    }

    public void setGetCardFronaddress(String getCardFronaddress) {
        this.getCardFronaddress = getCardFronaddress;
    }

    public String getDoctorSpecname() {
        return DoctorSpecname;
    }

    public void setDoctorSpecname(String doctorSpecname) {
        DoctorSpecname = doctorSpecname;
    }

    public String getDoctorSpecimage() {
        return DoctorSpecimage;
    }

    public void setDoctorSpecimage(String doctorSpecimage) {
        DoctorSpecimage = doctorSpecimage;
    }

    public String getDoctorSpechopital() {
        return DoctorSpechopital;
    }

    public void setDoctorSpechopital(String doctorSpechopital) {
        DoctorSpechopital = doctorSpechopital;
    }

    public String getDoctorSpecaddress() {
        return DoctorSpecaddress;
    }

    public void setDoctorSpecaddress(String doctorSpecaddress) {
        DoctorSpecaddress = doctorSpecaddress;
    }

    public String getDoctorSpecphone() {
        return DoctorSpecphone;
    }

    public void setDoctorSpecphone(String doctorSpecphone) {
        DoctorSpecphone = doctorSpecphone;
    }

    public String getDoctorSpecchungchi() {
        return DoctorSpecchungchi;
    }

    public void setDoctorSpecchungchi(String doctorSpecchungchi) {
        DoctorSpecchungchi = doctorSpecchungchi;
    }

    public String getCheckCall() {
        return CheckCall;
    }

    public void setCheckCall(String checkCall) {
        CheckCall = checkCall;
    }

    public String getServiceContent() {
        return ServiceContent;
    }

    public void setServiceContent(String serviceContent) {
        ServiceContent = serviceContent;
    }

    public String getServiceMoney() {
        return ServiceMoney;
    }

    public void setServiceMoney(String serviceMoney) {
        ServiceMoney = serviceMoney;
    }

    public String getServiceImage() {
        return ServiceImage;
    }

    public void setServiceImage(String serviceImage) {
        ServiceImage = serviceImage;
    }

    public String getServicehName() {
        return ServicehName;
    }

    public void setServicehName(String servicehName) {
        ServicehName = servicehName;
    }

    public String getResultXray() {
        return ResultXray;
    }

    public void setResultXray(String resultXray) {
        ResultXray = resultXray;
    }

    public String getAPI() {
        return API;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    public String getHomeTowAddress() {
        return HomeTowAddress;
    }

    public void setHomeTowAddress(String homeTowAddress) {
        HomeTowAddress = homeTowAddress;
    }

    public String getCheckTestAtHome() {
        return CheckTestAtHome;
    }

    public String getAddcard() {
        return addcard;
    }

    public void setAddcard(String addcard) {
        this.addcard = addcard;
    }

    public void setCheckTestAtHome(String checkTestAtHome) {
        CheckTestAtHome = checkTestAtHome;
    }

    public String getHomeOneDate() {
        return HomeOneDate;
    }

    public void setHomeOneDate(String homeOneDate) {
        HomeOneDate = homeOneDate;
    }

    public String getHomeOneTime() {
        return HomeOneTime;
    }

    public void setHomeOneTime(String homeOneTime) {
        HomeOneTime = homeOneTime;
    }

    public String getHomeOneType() {
        return HomeOneType;
    }

    public void setHomeOneType(String homeOneType) {
        HomeOneType = homeOneType;
    }

    public String getHomeOneSpecHospital() {
        return HomeOneSpecHospital;
    }

    public void setHomeOneSpecHospital(String homeOneSpecHospital) {
        HomeOneSpecHospital = homeOneSpecHospital;
    }

    public String getHomeOneNameUser() {
        return HomeOneNameUser;
    }

    public void setHomeOneNameUser(String homeOneNameUser) {
        HomeOneNameUser = homeOneNameUser;
    }

    public String getHomeOneGenderUser() {
        return HomeOneGenderUser;
    }

    public void setHomeOneGenderUser(String homeOneGenderUser) {
        HomeOneGenderUser = homeOneGenderUser;
    }

    public String getHomeOneAgeUser() {
        return HomeOneAgeUser;
    }

    public void setHomeOneAgeUser(String homeOneAgeUser) {
        HomeOneAgeUser = homeOneAgeUser;
    }

    public String getHomeOnePhoneUser() {
        return HomeOnePhoneUser;
    }

    public void setHomeOnePhoneUser(String homeOnePhoneUser) {
        HomeOnePhoneUser = homeOnePhoneUser;
    }

    public String getHomeOneAddressUser() {
        return HomeOneAddressUser;
    }

    public void setHomeOneAddressUser(String homeOneAddressUser) {
        HomeOneAddressUser = homeOneAddressUser;
    }

    public String getHomeOneNameHospital() {
        return HomeOneNameHospital;
    }

    public void setHomeOneNameHospital(String homeOneNameHospital) {
        HomeOneNameHospital = homeOneNameHospital;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Integer getLIKEORDIS() {
        return LIKEORDIS;
    }

    public void setLIKEORDIS(Integer LIKEORDIS) {
        this.LIKEORDIS = LIKEORDIS;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }



    public String getCommentImageFace() {
        return commentImageFace;
    }

    public void setCommentImageFace(String commentImageFace) {
        this.commentImageFace = commentImageFace;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentFullName() {
        return commentFullName;
    }

    public void setCommentFullName(String commentFullName) {
        this.commentFullName = commentFullName;
    }

    public String getCommentImage() {
        return commentImage;
    }

    public void setCommentImage(String commentImage) {
        this.commentImage = commentImage;
    }

    public String getCommentNumberLike() {
        return commentNumberLike;
    }

    public void setCommentNumberLike(String commentNumberLike) {
        this.commentNumberLike = commentNumberLike;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getFullNameStatic() {
        return fullNameStatic;
    }

    public void setFullNameStatic(String fullNameStatic) {
        this.fullNameStatic = fullNameStatic;
    }

    public String getEmailStatic() {
        return emailStatic;
    }

    public void setEmailStatic(String emailStatic) {
        this.emailStatic = emailStatic;
    }

    public String getPassWordStatic() {
        return passWordStatic;
    }

    public void setPassWordStatic(String passWordStatic) {
        this.passWordStatic = passWordStatic;
    }

    public String getPhoneStatic() {
        return phoneStatic;
    }

    public void setPhoneStatic(String phoneStatic) {
        this.phoneStatic = phoneStatic;
    }

    public String getByteArrayImageStatic() {
        return byteArrayImageStatic;
    }

    public void setByteArrayImageStatic(String byteArrayImageStatic) {
        this.byteArrayImageStatic = byteArrayImageStatic;
    }

    public String getDateOfBirthStatic() {
        return dateOfBirthStatic;
    }

    public void setDateOfBirthStatic(String dateOfBirthStatic) {
        this.dateOfBirthStatic = dateOfBirthStatic;
    }

    public String getSexStatic() {
        return sexStatic;
    }

    public void setSexStatic(String sexStatic) {
        this.sexStatic = sexStatic;
    }

    public String getCityStatic() {
        return cityStatic;
    }

    public void setCityStatic(String cityStatic) {
        this.cityStatic = cityStatic;
    }

    public String getTownshipStatic() {
        return townshipStatic;
    }

    public void setTownshipStatic(String townshipStatic) {
        this.townshipStatic = townshipStatic;
    }

    public String getWardStatic() {
        return wardStatic;
    }

    public void setWardStatic(String wardStatic) {
        this.wardStatic = wardStatic;
    }

    public String getApartmentNumberStatic() {
        return apartmentNumberStatic;
    }

    public void setApartmentNumberStatic(String apartmentNumberStatic) {
        this.apartmentNumberStatic = apartmentNumberStatic;
    }

    public float getMoneyStatic() {
        return moneyStatic;
    }

    public void setMoneyStatic(float moneyStatic) {
        this.moneyStatic = moneyStatic;
    }

    public int getAccumulatedPointsStatic() {
        return accumulatedPointsStatic;
    }

    public void setAccumulatedPointsStatic(int accumulatedPointsStatic) {
        this.accumulatedPointsStatic = accumulatedPointsStatic;
    }

    public int getCarepayactive() {
        return carepayactive;
    }

    public void setCarepayactive(int carepayactive) {
        this.carepayactive = carepayactive;
    }

    public boolean isCarepay() {
        return carepay;
    }

    public void setCarepay(boolean carepay) {
        this.carepay = carepay;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Uri getFacecheck() {
        return facecheck;
    }

    public void setFacecheck(Uri facecheck) {
        this.facecheck = facecheck;
    }

    public Uri getPaper1() {
        return paper1;
    }

    public void setPaper1(Uri paper1) {
        this.paper1 = paper1;
    }

    public Uri getPaper2() {
        return paper2;
    }

    public void setPaper2(Uri paper2) {
        this.paper2 = paper2;
    }

    public String getTransferCarePayPhone() {
        return TransferCarePayPhone;
    }

    public void setTransferCarePayPhone(String transferCarePayPhone) {
        TransferCarePayPhone = transferCarePayPhone;
    }

    public String getTransferCarePayName() {
        return TransferCarePayName;
    }

    public void setTransferCarePayName(String transferCarePayName) {
        TransferCarePayName = transferCarePayName;
    }

    public String getTransferCarePayContent() {
        return TransferCarePayContent;
    }

    public void setTransferCarePayContent(String transferCarePayContent) {
        TransferCarePayContent = transferCarePayContent;
    }

    public String getTransferCarePayAM() {
        return TransferCarePayAM;
    }

    public void setTransferCarePayAM(String transferCarePayAM) {
        TransferCarePayAM = transferCarePayAM;
    }

    public Integer getTransferCarePayCHECK() {
        return TransferCarePayCHECK;
    }

    public void setTransferCarePayCHECK(Integer transferCarePayCHECK) {
        TransferCarePayCHECK = transferCarePayCHECK;
    }

    public String getTransferBankPhone() {
        return TransferBankPhone;
    }

    public void setTransferBankPhone(String transferBankPhone) {
        TransferBankPhone = transferBankPhone;
    }

    public String getTransferBankName() {
        return TransferBankName;
    }

    public void setTransferBankName(String transferBankName) {
        TransferBankName = transferBankName;
    }

    public String getTransferBankContent() {
        return TransferBankContent;
    }

    public void setTransferBankContent(String transferBankContent) {
        TransferBankContent = transferBankContent;
    }

    public String getTransferBankAM() {
        return TransferBankAM;
    }

    public void setTransferBankAM(String transferBankAM) {
        TransferBankAM = transferBankAM;
    }

    public Integer getTransferBankCHECK() {
        return TransferBankCHECK;
    }

    public void setTransferBankCHECK(Integer transferBankCHECK) {
        TransferBankCHECK = transferBankCHECK;
    }

    public  Integer getCheckIsBank() {
        return CheckIsBank;
    }

    public  void setCheckIsBank(Integer checkIsBank) {
        CheckIsBank = checkIsBank;
    }

    public String getCommentIDPost() {
        return commentIDPost;
    }

    public void setCommentIDPost(String commentIDPost) {
        this.commentIDPost = commentIDPost;
    }

    public String getCommentIDPeople() {
        return commentIDPeople;
    }

    public void setCommentIDPeople(String commentIDPeople) {
        this.commentIDPeople = commentIDPeople;
    }
}
