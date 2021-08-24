[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# Kotlin Chat App
Support multi-clients chat together, and at the same, the LunLunBot will join the chat.

![img_1.png](img_1.png)

## Architecture
- [x] ***Ktor + Netty*** to use server functionality without having to rely on an external app container.
- [x] ***Ktor WebSocket*** the main communication mechanism for the chat.
- [x] ***Arrow-kt*** the FP coding style for Kotlin.
### Server
In charge of all the messages exchange for clients and the bridge to connect to the AI brain. 
> Main: /KtorWebsocketsChat/server/sre/main/kotlin/com/clluv/chat/server/Application.kt

### Client
The chat client, also send some commands to the server to let others know the client is adding/leavning to the chat.
> Main: /KtorWebsocketsChat/client/sre/main/kotlin/com/clluv/chat/client/ChatClient.kt

### Commands
Clients can type ***/commads*** to list all supporting commands
> /commands

## Terms
#### WebSockets
>Chat sessions are usually long-lived, with the client receiving messages from other participants over a long period of time.
> 
>Unlike regular HTTP requests, WebSocket connections can be kept open for a long time and have an easy interface for exchanging data between the client and server in the form of frames.
>
>We can think frames as WebSocket messages which come in different types (text, binary, close, ping/pong).
>
>Because Ktor provides high-level abstractions over the WebSocket protocol, we can concentrate on text and binary frames, and leave the handling of other frames to the framework.
#### Suspending Functions
>Suspending functions are the center of everything coroutines.
>
>A suspending function is simply a function that can be paused and resumed at a later time. 
> 
>They can execute a long-running operation and wait for it to complete without blocking.
#### Companion Objects
>An object declaration inside a class can be marked with the companion keyword.
> 
>Members of the companion object can be called simply by using the class name as the qualifier.
> 
>The name of the companion object can be omitted, in which case the name Companion will be used.
> 
>Class members can access the private members of the corresponding companion object.

## Reference
- [Creating a WebSocket Chat](https://ktor.io/docs/creating-web-socket-chat.html).
- [BrainShop by Acobat](https://brainshop.ai/).
- [Prepare Docker](https://ktor.io/docs/docker.html#prepare-docker-image).