package org.BluminEngine6.Legacy.Utils.EventSystem;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Action<t> {
    private List<IActionArgBased<t>> ListenersArg =  new ArrayList<>();
    private List<IAction> Listeners =  new ArrayList<>();

    private boolean CanInvoke = true;


    public void Invoke() {
        if(CanInvoke) {
            try {
                for (IActionArgBased<t> mr : ListenersArg) {
                    mr.Run();
                }
                for (IAction mr : Listeners) {
                    mr.Run();
                }
            } catch(ConcurrentModificationException e ){

            }
        }
    }

    public void Invoke(t args) {
        for (IActionArgBased<t> mr : ListenersArg) {
            mr.Run(args);
        }
    }
    public void addListener(IActionArgBased<t> act) {
        if(!ListenersArg.contains(act)) {
            ListenersArg.add(act);
        }
    }
    public void removeListener(IActionArgBased<t> act) {
        if(ListenersArg.contains(act)) {
            ListenersArg.remove(ListenersArg.lastIndexOf(act));
        }
    }
    public boolean IsListener(IActionArgBased<t> act) {
        if(ListenersArg.contains(act)) {
            return true;
        }
        return false;
    }

    public void addListener(IAction act) {
        if(!Listeners.contains(act)) {
            Listeners.add(act);
        }
    }

    public void removeListener(IAction act) {
        if(Listeners.contains(act)) {
            Listeners.remove(Listeners.lastIndexOf(act));
        }
    }
    public boolean IsListener(IAction act) {
        if(Listeners.contains(act)) {
            return true;
        }
        return false;
    }

    public boolean CanInvoke() {
        return CanInvoke;
    }

    public void setCanInvoke(boolean canInvoke) {
        CanInvoke = canInvoke;
    }

    public void addListener(Object o) {
    }
}
