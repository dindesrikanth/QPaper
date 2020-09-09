package com.example.questionpaper.Model;

public class Questionexplanation {
    public String mQuestion[]={
            "Which is the First planet in the solar systemiopopoppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp",
            "Which is the Second planet in the solar system",
            "Which is the Third planet in the solar system",
            "Which is the Forth planet in the solar system",
            "Which is the Fifth planet in the solar system",
            "Which is the Sixth planet in the solar system",
            "Which is the Seventh planet in the solar system",
            "Which is the Eighth planet in the solar system",
            "Which is the Ninth planet in the solar system"

    };
    private String mchoices[] []={
            {"Mercury","Venus","Mars","Earth"},
            {"Mercury","Venus","Mars","Earth"},
            {"Mercury","Venus","Mars","Earth"},
            {"Mercury","Venus","Mars","Earth"},
            {"Jupiter","Venus","Mars","Earth"},
            {"Mercury","Saturn","Mars","Earth"},
            {"Mercury","Venus","Uranus","Earth"},
            {"Mercury","Venus","Neptune","Earth"},
            {"Mercury","Pluto","Mars","Earth"},

    };
    private String correctans [] = {"Mercury","Venus","Earth","Mars","Jupiter","Saturn","Uranus","Neptune","Pluto"};
    public String correctansexp [] = {"Mercury is the first planet it is very hot planet","Venus is the second planet","Earth is third planet which is good for living","Mars is the forth plant","Jupiter iis the fivth plant its diameter is greter then all plants","Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius about nine times that of Earth. ... Saturn is named after the Roman god of wealth and agriculture; its astronomical symbol (♄) represents the god's sickle. ","Uranus (from the Latin name Ūranus for the Greek god Οὐρανός) is the seventh planet from the Sun. It has the third-largest planetary radius and fourth-largest planetary mass in the Solar System. ... Like the other giant planets, Uranus has a ring system, a magnetosphere, and numerous moons.","Neptune is the eighth and farthest known planet from the Sun in the Solar System. In the Solar System, it is the fourth-largest planet by diameter, the third-most-massive planet, and the densest giant planet. Neptune is 17 times the mass of Earth, slightly more massive than its near-twin Uranus.","Pluto (minor planet designation: 134340 Pluto) is an icy dwarf planet in the Kuiper belt, a ring of bodies beyond the orbit of Neptune. ... That definition excluded Pluto and reclassified it as a dwarf planet. It is the ninth-largest and tenth-most-massive known object directly orbiting the Sun."};

    public String getQuestion(int a)
    {
        String Question = mQuestion[a];
        return  Question;
    };
    public String getChoice1(int a)
    {
        String Question = mchoices[a] [0];
        return  Question;
    };
    public String getChoice2(int a)
    {
        String Question = mchoices[a] [1];
        return  Question;
    };
    public String getChoice3(int a)
    {
        String Question = mchoices[a] [2];
        return  Question;
    };
    public String getChoice4(int a)
    {
        String Question = mchoices[a] [3];
        return  Question;
    };
    public String getcorrectans(int a){
        String answer= correctans[a];
        return  answer;


    }
    public String getcorrectansexp(int a){
        String answerexp= correctansexp[a];
        return  answerexp;


    }
}
