package gigachad.security.services;

import gigachad.security.dto.PussyDto;

import java.util.List;

public interface PussyService {
    List<PussyDto> getAll();
    void add(PussyDto pussyDto);
    void destroyPussy(PussyDto pussyDto);
    void change(PussyDto pussyDto);
    PussyDto findById(long id) throws Exception;
    void setOwner(long pussyId, long ownerId) throws Exception;
    void addFriend(long firstPussyId, long secondPussyId);
    List<PussyDto> getFriends(long pussyId);
}
