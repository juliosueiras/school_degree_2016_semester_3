package com.juliosueiras.midterm;

// import android.os.Parcelable;
import android.os.Parcelable;
import android.os.Parcel;

public class Pizza implements Parcelable {
    private String size;
    private boolean hasCheese;
    private boolean hasPepperoni;
    private boolean hasGreenPepper;

    public Pizza(String size) {
        this.setSize(size);
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setHasCheese(boolean hasCheese) {
        this.hasCheese = hasCheese;
    }

    public boolean hasCheese() {
        return hasCheese;
    }

    public void setHasPepperoni(boolean hasPepperoni) {
        this.hasPepperoni = hasPepperoni;
    }

    public boolean hasPepperoni() {
        return hasPepperoni;
    }

    public void setHasGreenPepper(boolean hasGreenPepper) {
        this.hasGreenPepper = hasGreenPepper;
    }

    public boolean hasGreenPepper() {
        return hasGreenPepper;
    }

    public Pizza(Parcel in){
        String[] data = new String[1];
        boolean[] dataBool = new boolean[3];

        in.readStringArray(data);
        in.readBooleanArray(dataBool);
        this.setSize(data[0]);
        this.setHasCheese(dataBool[0]);
        this.setHasPepperoni(dataBool[1]);
        this.setHasGreenPepper(dataBool[2]);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.getSize()});
        dest.writeBooleanArray(new boolean[] {this.hasCheese(), this.hasPepperoni(), this.hasGreenPepper()});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };

}
