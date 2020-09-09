package com.example.questionpaper.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Questionesmodel implements Parcelable {
    private Long course_id;
    private Long sub_id;
    private Long test_id;
    private Long ques_id;
    private Long ques_local_id;
    private String ques_detail;
    private String opta;
    private String optb;
    private String optc;
    private String optd;
    private String opte;
    private String answerId;
    private boolean isMarked;
    private boolean isSeen;

    public Questionesmodel(Long course_id, Long sub_id, Long test_id, Long ques_id, Long ques_local_id, String ques_detail, String opta, String optb, String optc, String optd, String opte, String answerId, boolean isMarked, boolean isSeen) {
        this.course_id = course_id;
        this.sub_id = sub_id;
        this.test_id = test_id;
        this.ques_id = ques_id;
        this.ques_local_id = ques_local_id;
        this.ques_detail = ques_detail;
        this.opta = opta;
        this.optb = optb;
        this.optc = optc;
        this.optd = optd;
        this.opte = opte;
        this.answerId = answerId;
        this.isMarked = isMarked;
        this.isSeen = isSeen;
    }

    protected Questionesmodel(Parcel in) {
        if (in.readByte() == 0) {
            course_id = null;
        } else {
            course_id = in.readLong();
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
        ques_detail = in.readString();
        opta = in.readString();
        optb = in.readString();
        optc = in.readString();
        optd = in.readString();
        opte = in.readString();
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

    public Long getQues_local_id() {
        return ques_local_id;
    }

    public void setQues_local_id(Long ques_local_id) {
        this.ques_local_id = ques_local_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getSub_id() {
        return sub_id;
    }

    public void setSub_id(Long sub_id) {
        this.sub_id = sub_id;
    }

    public Long getTest_id() {
        return test_id;
    }

    public void setTest_id(Long test_id) {
        this.test_id = test_id;
    }

    public Long getQues_id() {
        return ques_id;
    }

    public void setQues_id(Long ques_id) {
        this.ques_id = ques_id;
    }

    public String getQues_detail() {
        return ques_detail;
    }

    public void setQues_detail(String ques_detail) {
        this.ques_detail = ques_detail;
    }

    public String getOpta() {
        return opta;
    }

    public void setOpta(String opta) {
        this.opta = opta;
    }

    public String getOptb() {
        return optb;
    }

    public void setOptb(String optb) {
        this.optb = optb;
    }

    public String getOptc() {
        return optc;
    }

    public void setOptc(String optc) {
        this.optc = optc;
    }

    public String getOptd() {
        return optd;
    }

    public void setOptd(String optd) {
        this.optd = optd;
    }

    public String getOpte() {
        return opte;
    }

    public void setOpte(String opte) {
        this.opte = opte;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (course_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(course_id);
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
        dest.writeString(ques_detail);
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
