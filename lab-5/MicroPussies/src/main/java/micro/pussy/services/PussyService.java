package micro.pussy.services;


import micro.pussy.dto.PussyDto;

import java.util.List;

public interface PussyService {
    List<PussyDto> getAll();
    void add(PussyDto pussyDto);
    void destroyPussy(PussyDto pussyDto);
    void change(PussyDto pussyDto);
    PussyDto findById(long id) throws Exception;
    void addFriend(long firstPussyId, long secondPussyId);
    List<PussyDto> getFriends(long pussyId);
}
