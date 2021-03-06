package Objects;

import java.io.Serializable;
import java.util.LinkedList;

public class Stop implements Serializable {
    private Location location;
    private LinkedList<Machine> collect;
    private LinkedList<Machine> drop;
    public boolean depo = false;

    public Stop(Location location, Machine collectOrDrop, Request.Type type)
    {
        this.location = location;
        this.drop = new LinkedList<>();
        this.collect = new LinkedList<>();

        if(type == Request.Type.TEMPORARYCOLLECT || type == Request.Type.COLLECT)
        {
            addCollect(collectOrDrop);
        }
        else if(type == Request.Type.DROP )
        {
            addDrop(collectOrDrop,false);
        }

    }

    // For clone
    public Stop(Location location, LinkedList<Machine> collect, LinkedList<Machine> drop, boolean depo) {
        this.location = location;
        this.collect = collect;
        this.drop = drop;
        this.depo = depo;
    }

    public void addCollect(Machine m)
    {
        //Wanneer je een machine moet collecten en droppen op dezelfde plaats mag je deze maar 1 keer noteren in de vallidator daarom deze bewerking

        boolean zitReedsInDrop = false;
        for(Machine ma : drop)
        {
            if(ma.getId() == m.getId())
            {
                zitReedsInDrop = true;
            }
        }
        if(zitReedsInDrop == false)
        {
            collect.add(m);
        }
    }

    public void removeCollect(Machine m)
    {
        collect.remove(m);
    }

    public void removeDrop(Machine m)
    {
        drop.remove(m);
    }

    //speciaalGeval is true als een truck dezelfde machine moet collecten en dropen op dezelfde plaats!
    public void addDrop(Machine m, boolean speciaalGeval)
    {
        boolean zitReedsInCollect = false;
        for(Machine ma : collect)
        {
            if(ma.getId() == m.getId())
            {
                zitReedsInCollect = true;
            }
        }
        if(zitReedsInCollect == false || speciaalGeval == true)
        {
            drop.add(m);
        }
    }

    public Location getLocation()
    {
        return location;
    }

    public LinkedList<Machine> getcollect()
    {
        return collect;
    }

    public LinkedList<Machine> getdrop()
    {
        return drop;
    }
}
