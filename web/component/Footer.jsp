<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="./style.css">

    </head>

    <body style="border-top: 1px solid var(--Secondary-700, #242535); background: var(--Secondary-900, #141624);">
        <div style = "display: flex; padding: 64px 352px 0px 352px; flex-direction: column; align-items: flex-start; gap: 64px; width: 100%">
            <div style="width: 1920px; height: 500px; padding-top: 64px; padding-left: 352px; padding-right: 352px; background: #131212; border-top: 1px #242535 solid; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 64px; display: inline-flex">
                <div style="justify-content: flex-start; align-items: flex-start; gap: 20px; display: inline-flex">
                    <div style="width: 289px; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 24px; display: inline-flex">
                        <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 12px; display: flex">
                            <div style="color: white; font-size: 18px; font-family: Plus Jakarta Sans; font-weight: 600; line-height: 28px; word-wrap: break-word">About</div>
                            <div style="width: 280px; color: #97989F; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam</div>
                        </div>
                        <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 4px; display: flex">
                            <div><span style="color: white; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 600; line-height: 24px; word-wrap: break-word">Email : </span><span style="color: #97989F; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">info@jstemplate.net</span></div>
                            <div><span style="color: white; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 600; line-height: 24px; word-wrap: break-word">Phone : </span><span style="color: #97989F; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">880 123 456 789</span></div>
                        </div>
                    </div>
                    <div style="height: 236px; justify-content: center; align-items: flex-start; gap: 80px; display: flex">
                        <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 24px; display: inline-flex">
                            <div style="color: white; font-size: 18px; font-family: Plus Jakarta Sans; font-weight: 600; line-height: 28px; word-wrap: break-word">Quick Link</div>
                            <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 8px; display: flex">
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Home</div>
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">About</div>
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Blog</div>
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Archived</div>
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Author</div>
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Contact</div>
                            </div>
                        </div>
                        <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 24px; display: inline-flex">
                            <div style="color: white; font-size: 18px; font-family: Plus Jakarta Sans; font-weight: 600; line-height: 28px; word-wrap: break-word">Category</div>
                            <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 8px; display: flex">
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Lifestyle</div>
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Technology</div>
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Travel</div>
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Business</div>
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Economy</div>
                                <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Sports</div>
                            </div>
                        </div>
                    </div>
                    <div style="width: 392px; padding: 32px; background: #242535; border-radius: 12px; flex-direction: column; justify-content: flex-start; align-items: center; gap: 30px; display: inline-flex">
                        <div style="flex-direction: column; justify-content: flex-start; align-items: center; gap: 8px; display: flex">
                            <div style="text-align: center; color: white; font-size: 20px; font-family: Work Sans; font-weight: 600; line-height: 24px; word-wrap: break-word">Weekly Newsletter</div>
                            <div style="text-align: center; color: #97989F; font-size: 16px; font-family: Work Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Get blog articles and offers via email</div>
                        </div>
                        <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 8px; display: flex">
                            <div style="height: 48px; flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 4px; display: flex">
                                <div style="align-self: stretch; padding-left: 16px; padding-right: 16px; padding-top: 12px; padding-bottom: 12px; background: #181A2A; border-radius: 6px; border: 1px #3B3C4A solid; justify-content: flex-start; align-items: center; gap: 10px; display: inline-flex">
                                    <div style="flex: 1 1 0; color: #97989F; font-size: 16px; font-family: Work Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Your Email</div>
                                    <div style="width: 20px; height: 20px; position: relative">
                                        <div style="width: 17.50px; height: 13.75px; left: 1.25px; top: 3.12px; position: absolute; background: #696A75"></div>
                                    </div>
                                </div>
                            </div>
                            <div style="align-self: stretch; padding-left: 20px; padding-right: 20px; padding-top: 12px; padding-bottom: 12px; background: #4B6BFB; border-radius: 6px; overflow: hidden; justify-content: center; align-items: center; gap: 12px; display: inline-flex">
                                <div style="color: white; font-size: 16px; font-family: Work Sans; font-weight: 500; line-height: 24px; word-wrap: break-word">Subscribe</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="width: 1216px; padding-top: 32px; padding-bottom: 32px; border-top: 1px #242535 solid; justify-content: flex-start; align-items: center; gap: 64px; display: inline-flex">
                    <div style="justify-content: flex-start; align-items: center; gap: 10px; display: flex">
                        <div style="width: 48px; height: 48px; background: white"></div>
                        <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 2px; display: inline-flex">
                            <div><span style="color: white; font-size: 20px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 28px; word-wrap: break-word">Meta</span><span style="color: white; font-size: 20px; font-family: Plus Jakarta Sans; font-weight: 800; line-height: 28px; word-wrap: break-word">Blog</span></div>
                            <div><span style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">© </span><span style="color: white; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">JS Template</span><span style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word"> 2023. All Rights Reserved.</span></div>
                        </div>
                    </div>
                    <div style="flex: 1 1 0; height: 24px; justify-content: flex-end; align-items: center; gap: 16px; display: flex">
                        <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Terms of Use</div>
                        <div style="width: 24px; height: 0px; transform: rotate(90deg); transform-origin: 0 0; border: 1px #242535 solid"></div>
                        <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Privacy Policy</div>
                        <div style="width: 24px; height: 0px; transform: rotate(90deg); transform-origin: 0 0; border: 1px #242535 solid"></div>
                        <div style="color: #BABABF; font-size: 16px; font-family: Plus Jakarta Sans; font-weight: 400; line-height: 24px; word-wrap: break-word">Cookie Policy</div>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>