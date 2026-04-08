# BiliBiliVideo PlaceholderAPI Expansion

[English](#english) | [简体中文 (zh_cn)](#简体中文-zh_cn)

---

## English

This is the PlaceholderAPI expansion for the **BiliBiliVideo** plugin. It allows you to display a player's Bilibili binding information, credential status, and their interaction data (Like, Coin, Favorite, Triple) with specific Bilibili videos directly in any PAPI-supported plugin.

### 📝 Placeholders

| Placeholder | Description |
| :--- | :--- |
| `%bilibilivideo_name%` | Returns the Minecraft player's name associated with the Bilibili binding. |
| `%bilibilivideo_uid%` | Returns the player's bound Bilibili UID (Mid). |
| `%bilibilivideo_nickname%` | Returns the player's bound Bilibili Nickname. |
| `%bilibilivideo_status%` | Returns the status of the player's credential (e.g., Normal, Expired, Disabled). |
| `%bilibilivideo_label%` | Returns the label of the player's credential. |
| `%bilibilivideo_binduid%` | Returns the Bilibili UID bound to the current credential or fallback binding. |
| `%bilibilivideo_triple_liked_<bvid>%` | Returns whether the player has liked the specified video (e.g., `yes`/`no`). |
| `%bilibilivideo_triple_coincount_<bvid>%`| Returns the number of coins the player has dropped on the specified video. |
| `%bilibilivideo_triple_favoured_<bvid>%` | Returns whether the player has favorited the specified video (e.g., `yes`/`no`).|
| `%bilibilivideo_triple_istriple_<bvid>%` | Returns whether the player has done a "Triple" (Like + Coin + Favorite) on the video. |

*Example Usage:* `%bilibilivideo_triple_istriple_BV1xx411c7mD%`

> **Pro Tip:** If your `<bvid>` contains underscores or special characters, you can wrap it in quotes to prevent parsing issues: `%bilibilivideo_triple_liked_"BV1xx_Example"%`.

### ⚙️ Configuration

You can customize the output texts in the `PlaceholderAPI/config.yml` file under the `bilibilivideo` section:

```yaml
bilibilivideo:
  status:
    disabled: "禁用"
    normal: "正常"
    expired: "过期"
    unknown: "未知(%status%)"
  boolean:
    true: "yes"
    false: "no"
  options:
    null_value: "null"
    triple_error: "default" # 根据 BiliBiliVideo 内部信息决定提示！

