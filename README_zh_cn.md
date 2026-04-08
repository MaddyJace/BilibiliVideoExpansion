# BiliBiliVideo PlaceholderAPI Expansion

> 插件的官方 PlaceholderAPI 扩展文档。

---

## 简体中文 (zh_cn)

这是 **BiliBiliVideo** 插件的 PlaceholderAPI 扩展。 它允许你在任何支持 PAPI 的插件中直接显示玩家的 Bilibili 绑定信息、凭据状态，以及他们与特定 Bilibili 视频的交互数据（点赞、投币、收藏、一键三连）。

### 📝 占位符
| 占位符 | 描述 |
| :--- | :--- |
| `%bilibilivideo_name%` | 返回与 Bilibili 绑定关联的 Minecraft 玩家名称。 |
| `%bilibilivideo_uid%` | 返回玩家绑定的 Bilibili UID (Mid)。 |
| `%bilibilivideo_nickname%` | 返回玩家绑定的 Bilibili 昵称。 |
| `%bilibilivideo_status%` | 返回玩家凭据的状态（例如：正常、过期、禁用）。 |
| `%bilibilivideo_label%` | 返回玩家凭据的标签。 |
| `%bilibilivideo_binduid%` | 返回绑定到当前凭据或备用绑定的 Bilibili UID。 |
| `%bilibilivideo_triple_liked_<bvid>%` | 返回玩家是否已点赞指定视频（例如：`yes`/`no`）。 |
| `%bilibilivideo_triple_coincount_<bvid>%`| 返回玩家为指定视频投币的数量。 |
| `%bilibilivideo_triple_favoured_<bvid>%` | 返回玩家是否已收藏指定视频（例如：`yes`/`no`）。 |
| `%bilibilivideo_triple_istriple_<bvid>%` | 返回玩家是否对该视频完成了“一键三连”（点赞 + 投币 + 收藏）。 |

*使用示例：* `%bilibilivideo_triple_istriple_BV1xx411c7mD%`

> **提示：** 如果你的 `<bvid>` 包含下划线或特殊字符，你可以用引号将其括起来以防止解析错误：`%bilibilivideo_triple_liked_"BV1xx_Example"%`。

### ⚙️ 配置

你可以在 `PlaceholderAPI/config.yml` 文件的 `bilibilivideo` 部分自定义输出文本：

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