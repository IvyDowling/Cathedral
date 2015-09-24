
import combatsystem.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class JUnit {
//
//    List<Weapon> wepList1 = new LinkedList<>();
//    List<Weapon> wepList2 = new LinkedList<>();

    //l,w,bSharp,howSharp?
    Weapon zeros = new Weapon(0, 0);
    Weapon poleArm = new Weapon(5, 10);
    Weapon longsword = new Weapon(3, 2, true, 10);
    Weapon dagger = new Weapon(1, 1, true, 30);
    Weapon battleAxe = new Weapon(2, 10, true, 10);

//    //h,w,str,dex
//    Entity e1 = new Entity(6, 200, 50, 90, wepList1); // e1 is faster
//    Entity e2 = new Entity(6, 180, 70, 70, wepList2); // e2 is stronger
//    Entity zeroEntity = new Entity(0, 0, 0, 0, zeros); //has only the 0,0 weapon
    CombatSystem cs = new CombatSystem();

//    Action act1 = new AttackAction(e1, e2, BodyComponent.TORSO);
//    Action act2 = new DodgeAction(e2);
    @Test
    public void combatSystemQueueTests() {
        //setup
        List<Weapon> wepList1 = new LinkedList<>();
        List<Weapon> wepList2 = new LinkedList<>();
        Entity e1 = new Entity(6, 200, 50, 90, wepList1); // e1 is faster
        Entity e2 = new Entity(6, 180, 70, 70, wepList2); // e2 is stronger
        Action act1 = new AttackAction(e1, e2, BodyComponent.TORSO);
        Action act2 = new DodgeAction(e2);
        //just setup

        cs.addAction(act1);
        cs.addAction(act2);

        Action turn = cs.getNextAction();   //prioritized by dex
        //aserts
        assertEquals(act1, turn);
        turn = cs.getNextAction();
        assertEquals(act2, turn);
        assertTrue(cs.isEmpty());
    }

    @Test
    public void weaponTest() {
        List<Weapon> wepList1 = new LinkedList<>();
        List<Weapon> wepList2 = new LinkedList<>();

        wepList1.add(longsword);
        wepList1.add(dagger);

        wepList2.add(poleArm);
        wepList2.add(dagger);

        //h,w,str,dex
        Entity e1 = new Entity(6, 200, 50, 90, wepList1); // e1 is faster
        Entity e2 = new Entity(6, 180, 70, 70, wepList2); // e2 is stronger

        assertTrue(e1.getDex() > e2.getDex());
        assertTrue(e2.getDamage() > e1.getDamage());
    }

    @Test
    public void nullsAndZeros() {
        Entity zeroEntity = new Entity(0, 0, 0, 0, zeros); //has only the 0,0 weapon
        assertEquals(zeroEntity.getDamage(), 2 * zeroEntity.getStr()); //two empty hands = punch * 2

        assertEquals(zeroEntity.getBody(), new Body(0,0));
    }
}
