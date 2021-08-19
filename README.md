[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# Kotlin Chat App

### WebSockets
Chat sessions are usually long-lived, with the client receiving messages from other participants over a long period of time.

Unlike regular HTTP requests, WebSocket connections can be kept open for a long time and have an easy interface for exchanging data between the client and server in the form of frames.
We can think frames as WebSocket messages which come in different types (text, binary, close, ping/pong).
Because Ktor provides high-level abstractions over the WebSocket protocol, we can concentrate on text and binary frames, and leave the handling of other frames to the framework.

###Reference
[Creating a WebSocket Chat](https://ktor.io/docs/creating-web-socket-chat.html). 
