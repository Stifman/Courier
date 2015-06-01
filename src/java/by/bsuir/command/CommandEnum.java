/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.command.ActionCommand;
import by.bsuir.command.AddCourierCommand;
import by.bsuir.command.AddParcelCommand;
import by.bsuir.command.DelCourierCommand;
import by.bsuir.command.FinishUpdCourierCommand;
import by.bsuir.command.GoAddParcelCommand;
import by.bsuir.command.GoDelCourierCommand;
import by.bsuir.command.GoUpdCourierCommand;
import by.bsuir.command.UpdCourierCommand;
import by.bsuir.command.ViewAllCouriersCommand;
import by.bsuir.command.ViewAllParcelsCommand;
import by.bsuir.command.GoDelParcelCommand;
import by.bsuir.command.DelParcelCommand;
import by.bsuir.command.FinishMarkParcelCommand;
import by.bsuir.command.FinishUpdParcelCommand;
import by.bsuir.command.GoSenderInfoCommand;
import by.bsuir.command.GoUpdParcelCommand;
import by.bsuir.command.HeadCommand;
import by.bsuir.command.MarkParcelCommand;
import by.bsuir.command.SenderInfoCommand;
import by.bsuir.command.UpdParcelCommand;

/**
 *
 * @author Степан
 */
public enum CommandEnum {

    HEAD {
                {
                    this.command = new HeadCommand();
                }
            },
    ADDPARCEL {
                {
                    this.command = new AddParcelCommand();
                }
            },
    GOADDPARCEL {
                {
                    this.command = new GoAddParcelCommand();
                }
            },
    GOUPDPARCEL {
                {
                    this.command = new GoUpdParcelCommand();
                }
            },
    UPDPARCEL {
                {
                    this.command = new UpdParcelCommand();
                }
            },
    FINISHUPDPARCEL {
                {
                    this.command = new FinishUpdParcelCommand();
                }
            },
    GODELPARCEL {
                {
                    this.command = new GoDelParcelCommand();
                }
            },
    DELPARCEL {
                {
                    this.command = new DelParcelCommand();
                }
            },
    VIEWALLPARCELS {
                {
                    this.command = new ViewAllParcelsCommand();
                }
            },
    MARKPARCEL {
                {
                    this.command = new MarkParcelCommand();
                }
            },
    FINISHMARKPARCEL {
                {
                    this.command = new FinishMarkParcelCommand();
                }
            },
    ADDCOURIER {
                {
                    this.command = new AddCourierCommand();
                }
            },
    UPDCOURIER {
                {
                    this.command = new UpdCourierCommand();
                }
            },
    FINISHUPDCOURIER {
                {
                    this.command = new FinishUpdCourierCommand();
                }
            },
    GOUPDCOURIER {
                {
                    this.command = new GoUpdCourierCommand();
                }
            },
    GODELCOURIER {
                {
                    this.command = new GoDelCourierCommand();
                }
            },
    DELCOURIER {
                {
                    this.command = new DelCourierCommand();
                }
            }, 
    VIEWALLCOURIERS {
                {
                    this.command = new ViewAllCouriersCommand();
                }
            },
    GOSENDERINFO {
                {
                    this.command = new GoSenderInfoCommand();
                }
            },
    SENDERINFO {
                {
                    this.command = new SenderInfoCommand();
                }
            };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
