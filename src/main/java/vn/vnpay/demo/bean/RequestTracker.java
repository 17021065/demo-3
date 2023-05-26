package vn.vnpay.demo.bean;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.springframework.stereotype.Component;

@Component
public class RequestTracker {
    public String getTrackingString() {
        return NanoIdUtils.randomNanoId();
    }
}
