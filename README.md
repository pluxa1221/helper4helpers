<div align="center">
    <h1>Helper4Helpers</h1>
    <a href="#">English</a> │ <a href="README.ru.md">Русский</a>
</div>

**Helper4Helpers** is a lightweight, high-performance ticket and question management system designed for Minecraft servers. It provides a seamless connection between players who need help and support staff who deliver answers.

---

### Why choose Helper4Helpers?

* **Race Condition Protection:** Every single question gets a unique, static sequential ID. If multiple helpers are answering tickets at the same time, the list *never* shifts or messes up.
* **Clickable UI in Chat:** Staff members can type `/answer` to see all open questions. Every row is interactive — just click on a question, and the command is instantly ready in your chat input!
* **Ultra-Lightweight:** Built using native Paper/Purpur API methods. Zero impact on your server's TPS or performance.

---

### Commands & Permissions

| Command | Description | Permission Node | Default |
| :--- | :--- | :--- | :--- |
| `/ask <text>` | Submit a new question | `helper4helpers.ask` | **Everyone** (`true`) |
| `/answer` | View a list of active questions | `helper4helpers.answer` | **Staff** (`op`) |
| `/answer <id> <text>` | Reply to a specific question | `helper4helpers.answer` | **Staff** (`op`) |

---

### Interactive Staff Overview

When a helper types `/answer` without arguments, the plugin generates a clean, structured overview:

```text
=== Active Questions ===
1. How do I claim my residence?
2. Where can I find the server rules?
3. My question is neither the first nor the last...
```

---

### Compatibility

* **Verified Support:** Paper / Purpur **1.21+** (Internal API version `26.2`).
* Supports modern **Adventure/MiniMessage** formatting for custom messages.

---

### Future Updates (Roadmap)

* [ ] **Vault Economy Integration:** Automatically reward helpers with money or points for answering questions.
* [ ] **Discord Logging:** Send every ticket and reply directly to your staff Discord channel via webhooks.

## License

Copyright (C) 2026 Nevetime

Licensed under the GNU GPLv3.
