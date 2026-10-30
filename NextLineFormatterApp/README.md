# NextLineFormatterApp

## Overview

A small Spring Boot HTTP service that inserts exactly one LF (`\n`) after each comma/period/colon group unless that group is already followed by a line break (LF, CRLF, or lone CR) or ends the input.

The formatter is stateless and scans the input once, writing into a local `StringBuilder`. Consecutive punctuation marks form one group and receive a single break, existing line breaks are never duplicated, and no trailing newline is added.

## Technology stack and versions

| Component | Version / notes |
| --- | --- |
| Java | 21 |
| Spring Boot | 4.1.2 |
| Web framework | Spring Web MVC (`spring-boot-starter-webmvc`) |
| Embedded server | Tomcat (Spring Boot default) |
| Build tool | Maven Wrapper, Maven 3.10.0 distribution |
| Test stack | JUnit Jupiter, Spring `MockMvc` |

## Prerequisites

- JDK 21 installed and available as `java` / `javac`.
- No local Maven installation is required; the wrapper downloads Maven 3.10.0 on first use.
- Network access on the first build so Maven can download dependencies.

Commands below are Windows-first and use `.\mvnw.cmd`. On macOS or Linux, use `./mvnw` instead. In Windows PowerShell, use `curl.exe` explicitly because `curl` is an alias for `Invoke-WebRequest`.

## Build

```powershell
.\mvnw.cmd -B clean package
```

macOS / Linux equivalent:

```bash
./mvnw -B clean package
```

This compiles the code, runs all tests, and produces the executable jar at `target\NextLineFormatterApp-0.0.1-SNAPSHOT.jar`.

## Run

With the Spring Boot Maven plugin:

```powershell
.\mvnw.cmd -B spring-boot:run
```

Or run the packaged jar:

```powershell
java -jar target\NextLineFormatterApp-0.0.1-SNAPSHOT.jar
```

The application listens on `http://localhost:8080` by default. Stop it with `Ctrl+C`.

## GUI

The app also serves an editor, so no separate frontend build is needed.

**The exact URL to open:**

```
http://localhost:8080/
```

Opening that address loads the GUI (`/api/v1/format` is called in the background, so the result is identical to the API). `GET /` redirects (`302`) to `/index.html`, which is served as static content by Spring Boot.

> **Display note.** The API preserves every character, including the space that follows an inserted break: `Hello, world.` → `Hello,\n world.`. In the *editor only*, that leading space on a wrapped line is visually trimmed client-side for tidier output (`Hello,\n world.` → `Hello,\nworld.`), so the pasted  display may show one fewer space than the raw API response. Copy and Save `.txt` use the trimmed editor text. To get the exact unmodified bytes, bypass the GUI and call `POST /api/v1/format` directly.

In the editor:

- Paste or type text in the left pane.
- Press **`Ctrl`+`Enter`** or click **Format** to format it. The result appears in the right pane.
- **Copy** copies the formatted text to the clipboard; **Save .txt** downloads a `formatted.txt` file.
- The char/line counters update live, and a status line reports success or any error.

## API

### `POST /api/v1/format`

- **Consumes:** `text/plain` (a charset parameter such as `charset=UTF-8` is accepted).
- **Produces:** `text/plain;charset=UTF-8`.
- **Request body:** the raw text to format.
- **Response body:** the formatted text, with no JSON wrapper.
- **Status codes:**
  - `200 OK` — formatted successfully.
  - `415 Unsupported Media Type` — the request declares an unsupported `Content-Type` (for example `application/json`).
  - `405 Method Not Allowed` — a request uses a method that is not mapped on this path, such as `GET`.

An empty request body is accepted: the service returns `200 OK` with an empty response body. A request without a body is treated the same way.

## Formatting rules

1. Insert one LF after each punctuation group that is followed by a character other than LF or CR; if the group ends the input, no LF is added.
2. Consecutive punctuation marks are one group and receive exactly one break:
   - `"a,.b"` becomes `"a,.\nb"`.
   - `"Wait... what?!"` becomes `"Wait...\n what?!"`.
   - `",.:"` is unchanged because there is no following character.
3. Never append a trailing newline:
   - `"a,"` stays `"a,"`.
   - `"done."` stays `"done."`.
4. Do not duplicate existing line breaks. An LF, CRLF, or lone CR immediately after punctuation is preserved as-is:
   - `"a,\nb"` is unchanged.
   - `"a.\r\nb"` is unchanged.
   - `"a,\rb"` is unchanged.
5. Leading punctuation is followed by a break: `",a"` becomes `",\na"`.
6. Punctuation followed by a space breaks before the space: `"a, "` becomes `"a,\n "`.
7. Every other character is preserved exactly, including tabs, spaces, Unicode, and emoji (including surrogate pairs).
8. Formatting is `O(n)` in the input length by construction. The service holds no state, so a single instance is thread-safe.

Example:

```text
Input:  Hello, world. Welcome: this is a formatter.
Output: Hello,
 world.
 Welcome:
 this is a formatter.
```

The output ends immediately after the final period; there is no trailing LF.

## curl examples

Run the application first, then use these from Windows PowerShell. `curl.exe` avoids the PowerShell `curl` alias.

### Basic request

```powershell
curl.exe -i -X POST http://localhost:8080/api/v1/format -H "Content-Type: text/plain; charset=UTF-8" --data-binary "Hello, world. Welcome: this is a formatter."
```

Expected status: `200`. Expected response header: `Content-Type: text/plain;charset=UTF-8`. Expected body:

```text
Hello,
 world.
 Welcome:
 this is a formatter.
```

No newline is appended after the final period.

### Unicode and emoji

```powershell
curl.exe -X POST http://localhost:8080/api/v1/format -H "Content-Type: text/plain; charset=UTF-8" --data-binary "héllo, wörld 🌍. naïve: café 🚀"
```

Expected body:

```text
héllo,
 wörld 🌍.
 naïve:
 café 🚀
```

Windows PowerShell 5.1 converts native command arguments using the system code page, so emoji can be altered before curl runs. If that happens, write the text to a UTF-8 file without a BOM and post that file:

```powershell
[IO.File]::WriteAllText("$env:TEMP\unicode.txt", "héllo, wörld 🌍. naïve: café 🚀", (New-Object System.Text.UTF8Encoding($false)))
curl.exe -X POST http://localhost:8080/api/v1/format -H "Content-Type: text/plain; charset=UTF-8" --data-binary "@$env:TEMP\unicode.txt"
```

### Empty body

Windows PowerShell 5.1 drops a literal empty argument such as `--data-binary ""`, so create a zero-byte file and post it:

```powershell
[IO.File]::WriteAllBytes("$env:TEMP\empty.txt", [byte[]]@())
curl.exe -i -X POST http://localhost:8080/api/v1/format -H "Content-Type: text/plain; charset=UTF-8" --data-binary "@$env:TEMP\empty.txt"
```

Expected status: `200`. Expected body: empty (zero bytes). A request with no body at all returns the same result.

### Wrong content type

```powershell
curl.exe -i -X POST http://localhost:8080/api/v1/format -H "Content-Type: application/json" --data-binary "{}"
```

Expected status: `415 Unsupported Media Type`.

## Testing

```powershell
.\mvnw.cmd -B test
```

macOS / Linux equivalent:

```bash
./mvnw -B test
```

Test coverage is split into:

- `TextFormatterServiceTest` — plain JUnit unit tests: each delimiter, punctuation groups, existing LF/CRLF/CR, leading and trailing punctuation, whitespace, Unicode and emoji, exact character preservation, a large input, and concurrent use of one shared service instance.
- `FormatControllerTest` — `@WebMvcTest` slice with `MockMvc`: exact status codes, media types, response bodies, empty body, `415`, and `405`.
- `FormatEndpointIntegrationTest` — `@SpringBootTest` with `@AutoConfigureMockMvc`: end-to-end wiring through the full application context.
- `GuiPageTest` — verifies that `GET /index.html` serves the editor page containing the `#input` and `#output` elements and the `fetch('/api/v1/format')` call.
- `NextLineFormatterAppApplicationTests` — verifies that the application context loads.
