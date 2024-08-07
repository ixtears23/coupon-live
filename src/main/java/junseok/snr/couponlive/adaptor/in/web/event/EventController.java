package junseok.snr.couponlive.adaptor.in.web.event;

import junseok.snr.couponlive.application.event.port.in.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/events")
public class EventController {
    private final EventService eventService;

    @PostMapping("/random")
    public ResponseEntity<Void> createRandomEvent() {
        eventService.createRandomEvent();
        return ResponseEntity.ok(null);
    }
}
