package snore.me.blogappback.domain.info;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public String getServerTime() {
        return "Server time: " + LocalDateTime.now();
    }
}
