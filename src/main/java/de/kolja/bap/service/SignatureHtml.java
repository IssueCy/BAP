package de.kolja.bap.service;

public class SignatureHtml {

    public static final String PAGE = """
                        <!DOCTYPE html>
                        <html lang="de">
                        <head>
                            <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
                            <title>Unterschrift</title>
                            <style>
                                html, body {
                                    margin: 0;
                                    padding: 0;
                                    width: 100%;
                                    height: 100%;
                                    background: #f0f0f0;
                                    font-family: sans-serif;
                                }
                                
                                body {
                                    display: flex;
                                    flex-direction: column;
                                    align-items: center;
                                    justify-content: center;
                                }
                        
                                h2 {
                                    margin: 10px 0;
                                }
                        
                                #wrapper {
                                    width: 95vw;
                                    height: 60vh;
                                    max-width: 900px;
                                    background: white;
                                    border: 2px solid black;
                                }
                        
                                canvas {
                                    width: 100%;
                                    height: 100%;
                                    touch-action: none;
                                }
                        
                                button {
                                    margin: 12px;
                                    padding: 12px 26px;
                                    font-size: 16px;
                                }
                        
                                @media (orientation: landscape) {
                                    #wrapper {
                                        height: 75vh;
                                    }
                                }
                            </style>
                        </head>
                        <body>
                        
                            <h2>Bitte unterschreiben</h2>
                        
                            <div id="wrapper">
                                <canvas id="pad"></canvas>
                            </div>
                        
                            <button onclick="send()">Unterschrift senden</button>
                        
                            <script>
                                const canvas = document.getElementById("pad");
                                const ctx = canvas.getContext("2d");
                        
                                ctx.strokeStyle = "#000";
                                ctx.lineWidth = 3;
                                ctx.lineCap = "round";
                        
                                let drawing = false;
                        
                                function resizeCanvas() {
                                    const img = ctx.getImageData(0, 0, canvas.width, canvas.height);
                        
                                    const rect = canvas.getBoundingClientRect();
                                    canvas.width = rect.width;
                                    canvas.height = rect.height;
                        
                                    ctx.putImageData(img, 0, 0);
                                }
                        
                                window.addEventListener("resize", resizeCanvas);
                                resizeCanvas();
                        
                                function getPos(e) {
                                    const rect = canvas.getBoundingClientRect();
                                    const p = e.touches ? e.touches[0] : e;
                                    return {
                                        x: p.clientX - rect.left,
                                        y: p.clientY - rect.top
                                    };
                                }
                        
                                function start(e) {
                                    drawing = true;
                                    const pos = getPos(e);
                                    ctx.beginPath();
                                    ctx.moveTo(pos.x, pos.y);
                                }
                        
                                function move(e) {
                                    if (!drawing) return;
                                    e.preventDefault();
                                    const pos = getPos(e);
                                    ctx.lineTo(pos.x, pos.y);
                                    ctx.stroke();
                                }
                        
                                function end() {
                                    drawing = false;
                                }
                        
                                canvas.addEventListener("mousedown", start);
                                canvas.addEventListener("mousemove", move);
                                canvas.addEventListener("mouseup", end);
                                canvas.addEventListener("mouseleave", end);
                        
                                canvas.addEventListener("touchstart", start);
                                canvas.addEventListener("touchmove", move);
                                canvas.addEventListener("touchend", end);
                        
                                function send() {
                                    fetch("/upload", {
                                        method: "POST",
                                        body: canvas.toDataURL("image/png")
                                    }).then(() => alert("Unterschrift gesendet"));
                                }
                            </script>
                        
                        </body>
                        </html>
            """;
}
