package bilibilivideo.maddyjace;

import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import online.bingzi.bilibili.video.internal.bilibili.dto.TripleStatusResult;
import online.bingzi.bilibili.video.internal.service.BindingService;
import online.bingzi.bilibili.video.internal.service.CredentialService;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
public class BiliBiliVideoExpansion extends PlaceholderExpansion implements Configurable {

    private final static Map<String, Object> defaults = new HashMap<>();
    static {
        defaults.put("status.disabled", "禁用");
        defaults.put("status.normal",   "正常");
        defaults.put("status.expired",  "过期");
        defaults.put("status.unknown",  "未知(%status%)");
        defaults.put("boolean.true", "yes");
        defaults.put("boolean.false", "no");
        defaults.put("options.null_value", "null");
        defaults.put("options.triple_error", "default");
    }

    @Override
    public Map<String, Object> getDefaults() {
        return defaults;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "bilibilivideo";
    }

    @Override
    public @NotNull String getAuthor() {
        return "MaddyJace";
    }

    @Override
    public @NotNull String getVersion() {
        return "VERSION";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String identifier) {
        String nullFallback = getString("options.null_value", "null");
        if (player.getPlayer() == null) return nullFallback;

        List<String> list = splitString(identifier);
        if (list.isEmpty()) return nullFallback;

        BindingService.BindingInfo binding = BindingService.INSTANCE.getBoundAccount(player.getUniqueId().toString());
        if (binding != null) {
            switch (list.get(0).toLowerCase()) {
                case "name":
                    return binding.getPlayerName();
                case "uid":
                    return String.valueOf(binding.getBilibiliMid());
                case "nickname":
                    return binding.getBilibiliName();
            }
        }
        CredentialService.CredentialInfo credential = CredentialService.INSTANCE.getCredentialInfo(player.getPlayer());
        if (credential != null) {
            String statusText;
            switch (credential.getStatus()) {
                case 0: statusText = getString("status.disabled", "禁用"); break;
                case 1: statusText = getString("status.normal", "正常");   break;
                case 2: statusText = getString("status.expired", "过期");  break;
                default:
                    statusText = getString("status.unknown", "未知(%status%)")
                            .replace("%status%", String.valueOf(credential.getStatus()));
                    break;
            }
            switch (list.get(0).toLowerCase()) {
                case "status":
                    return statusText;
                case "label":
                    return credential.getLabel();
                case "binduid":
                    String mid = (credential.getBilibiliMid() != null)
                            ? String.valueOf(credential.getBilibiliMid())
                            : (binding != null ? String.valueOf(binding.getBilibiliMid()) : "nouid");
                    return String.valueOf(mid);
            }
        }

        if (list.get(0).equalsIgnoreCase("triple") && list.size() >= 3) {
            String bvid = list.get(2);
            String type = list.get(1).toLowerCase();
            CredentialService.TripleCheckResult result = CredentialService.INSTANCE.checkTripleByPlayer(player.getPlayer(), bvid);
            if (!result.getSuccess() || result.getTripleStatus() == null) {
                String customError = getString("options.triple_error", "default");
                if (customError.equalsIgnoreCase("default")) {
                    return result.getMessage();
                }
                return result.getMessage();
            }
            TripleStatusResult status = result.getTripleStatus();
            String boolTrue   = getString("boolean.true", "yes");
            String boolFalse  = getString("boolean.false", "no");
            String likeText   = status.getLiked() ? boolTrue : boolFalse;
            String coinText   = String.valueOf(status.getCoinCount());
            String favText    = status.getFavoured() ? boolTrue : boolFalse;
            String tripleText = status.isTriple() ? boolTrue : boolFalse;
            switch (type) {
                case "liked":     return likeText;
                case "coincount": return coinText;
                case "favoured":  return favText;
                case "istriple":  return tripleText;
            }
        }
        return nullFallback;
    }

    private static List<String> splitString(String input) {
        String regex = "_(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        List<String> parts = new ArrayList<>();
        for (String part : input.split(regex)) {
            // 分割之后去掉首尾引号
            if (part.startsWith("\"") && part.endsWith("\"") && part.length() >= 2) {
                part = part.substring(1, part.length() - 1);
            }
            parts.add(part);
        }
        return parts;
    }
}
