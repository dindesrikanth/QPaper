package com.example.questionpaper.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Questionesmodel implements Parcelable {
    private Long cource_id;
    private Long sub_id;
   private Long test_id;
    private Long ques_id;
    private Long ques_local_id;
    private String ques_details;
    private String testName;
    private String opta;
    private String optb;
    private String optc;
    private String optd;
    private String opte;

   private String answerId;
    private boolean isMarked;
    private boolean isSeen;
    private String imageURI;
    private Long dispOrderIndex;

    protected Questionesmodel(Parcel in) {
        if (in.readByte() == 0) {
            cource_id = null;
        } else {
            cource_id = in.readLong();
        }
        if (in.readByte() == 0) {
            sub_id = null;
        } else {
            sub_id = in.readLong();
        }
        if (in.readByte() == 0) {
            test_id = null;
        } else {
            test_id = in.readLong();
        }
        if (in.readByte() == 0) {
            ques_id = null;
        } else {
            ques_id = in.readLong();
        }
        if (in.readByte() == 0) {
            ques_local_id = null;
        } else {
            ques_local_id = in.readLong();
        }
        ques_details = in.readString();
        testName = in.readString();
        opta = in.readString();
        optb = in.readString();
        optc = in.readString();
        optd = in.readString();
        opte = in.readString();
        imageURI = in.readString();
        if (in.readByte() == 0) {
            dispOrderIndex = null;
        } else {
            dispOrderIndex = in.readLong();
        }
        answerId = in.readString();
        isMarked = in.readByte() != 0;
        isSeen = in.readByte() != 0;
    }

    public static final Creator<Questionesmodel> CREATOR = new Creator<Questionesmodel>() {
        @Override
        public Questionesmodel createFromParcel(Parcel in) {
            return new Questionesmodel(in);
        }

        @Override
        public Questionesmodel[] newArray(int size) {
            return new Questionesmodel[size];
        }
    };

    //public Questionesmodel(long cource_id, long sub_id, long test_id, long ques_id, long ques_local_id, String ques_detail, String opta, String optb, String optc, String optd, String opte, Object o, boolean b, boolean b1) {
    //}

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public Questionesmodel(Long cource_id, Long sub_id, Long test_id, Long ques_id, Long ques_local_id, String ques_details, String testName, String opta, String optb, String optc, String optd, String opte, String answerId, boolean isMarked, boolean isSeen, String imageURI, Long dispOrderIndex) {
        this.cource_id = cource_id;
        this.sub_id = sub_id;
        this.test_id = test_id;
        this.ques_id = ques_id;
        this.ques_local_id = ques_local_id;
        this.ques_details = ques_details;
        this.testName = testName;
        this.opta = opta;
        this.optb = optb;
        this.optc = optc;
        this.optd = optd;
        this.opte = opte;
        this.answerId = answerId;
        this.isMarked = isMarked;
        this.isSeen = isSeen;
        this.imageURI = imageURI;
        this.dispOrderIndex = dispOrderIndex;
    }

    public void setCource_id(Long cource_id) {
        this.cource_id = cource_id;
    }

    public void setSub_id(Long sub_id) {
        this.sub_id = sub_id;
    }

    public void setTest_id(Long test_id) {
        this.test_id = test_id;
    }

    public void setQues_id(Long ques_id) {
        this.ques_id = ques_id;
    }

    public void setQues_local_id(Long ques_local_id) {
        this.ques_local_id = ques_local_id;
    }

    public void setQues_details(String ques_details) {
        this.ques_details = ques_details;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setOpta(String opta) {
        this.opta = opta;
    }

    public void setOptb(String optb) {
        this.optb = optb;
    }

    public void setOptc(String optc) {
        this.optc = optc;
    }

    public void setOptd(String optd) {
        this.optd = optd;
    }

    public void setOpte(String opte) {
        this.opte = opte;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public void setDispOrderIndex(Long dispOrderIndex) {
        this.dispOrderIndex = dispOrderIndex;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public Long getCource_id() {
        return cource_id;
    }

    public Long getSub_id() {
        return sub_id;
    }

    public Long getTest_id() {
        return test_id;
    }

    public Long getQues_id() {
        return ques_id;
    }

    public Long getQues_local_id() {
        return ques_local_id;
    }

    public String getQues_details() {
        return ques_details;
    }

    public String getTestName() {
        return testName;
    }

    public String getOpta() {
        return opta;
    }

    public String getOptb() {
        return optb;
    }

    public String getOptc() {
        return optc;
    }

    public String getOptd() {
        return optd;
    }

    public String getOpte() {
        return opte;
    }

    public String getImageURI() {
        return imageURI;
    }

    public Long getDispOrderIndex() {
        return dispOrderIndex;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public boolean isSeen() {
        return isSeen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (cource_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(cource_id);
        }
        if (sub_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(sub_id);
        }
        if (test_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(test_id);
        }
        if (ques_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(ques_id);
        }
        if (ques_local_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(ques_local_id);
        }
        dest.writeString(ques_details);
        dest.writeString(opta);
        dest.writeString(optb);
        dest.writeString(optc);
        dest.writeString(optd);
        dest.writeString(opte);
       dest.writeString(answerId);
        dest.writeByte((byte) (isMarked ? 1 : 0));
        dest.writeByte((byte) (isSeen ? 1 : 0));
    }
}
