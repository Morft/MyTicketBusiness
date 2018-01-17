package myticket.myticketbusiness.activities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Service implements Parcelable{

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        public Service[] newArray(int size) {
            return new Service[size];
        }
    };

    private String providerId;
    private String providerName;
    private String service;
    private long actualTicket;
    private long avgTime;
    private List<String> usersInLine;

    public Service() {}

    public Service(String providerId, String providerName, String service, long actualTicket, long avgTime, List<String> usersInLine) {
        this.providerId = providerId;
        this.providerName = providerName;
        this.service = service;
        this.actualTicket = actualTicket;
        this.avgTime = avgTime;
        this.usersInLine = usersInLine;
    }

    // Serve para enviar um objeto para outra atividade
    public Service(Parcel in){
        this.providerId = in.readString();
        this.providerName = in.readString();
        this.service = in.readString();
        this.actualTicket = in.readLong();
        this.avgTime = in.readLong();
        this.usersInLine = in.createStringArrayList();
    }

    public String getProviderId() {
        return providerId;
    }

    public String getProviderName() {return providerName;}

    public String getService() {
        return service;
    }

    public long getActualTicket() {
        return actualTicket;
    }

    public long getAvgTime() {
        return avgTime;
    }

    public List<String> getUsersInLine() {
        return usersInLine;
    }

    @Override
    public String toString() {
        return "Service{" +
                "providerId='" + providerId + '\'' +
                ", providerName='" + providerName + '\'' +
                ", service='" + service + '\'' +
                ", actualTicket=" + actualTicket +
                ", avgTime=" + avgTime +
                ", usersInLine=" + usersInLine +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.providerId);
        dest.writeString(this.providerName);
        dest.writeString(this.service);
        dest.writeLong(this.actualTicket);
        dest.writeLong(this.avgTime);
        dest.writeStringList(this.usersInLine);
    }
}
