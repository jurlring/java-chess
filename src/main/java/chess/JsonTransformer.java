package chess;

import chess.dto.GameDto;
import chess.dto.MoveDto;
import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private final Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

    public Gson getGson() {
        return gson;
    }

    public GameDto fromJsonToGameDto(final String body, final Class<GameDto> dto) {
        return gson.fromJson(body, dto);
    }

    public MoveDto fromJsonToMoveDto(final String body, final Class<MoveDto> dto) {
        return gson.fromJson(body, dto);
    }

}

