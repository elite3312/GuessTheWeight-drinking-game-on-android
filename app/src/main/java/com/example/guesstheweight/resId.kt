package com.example.guesstheweight

class resId {
   fun getID(face:Int,suit:Int):Int{
        if(face==1&&suit==1)return R.drawable.onec
        if(face==1&&suit==2)return R.drawable.oned
        if(face==1&&suit==3)return R.drawable.oneh
        if(face==1&&suit==4)return R.drawable.ones
        if(face==2&&suit==1)return R.drawable.twoc
        if(face==2&&suit==2)return R.drawable.twod
        if(face==2&&suit==3)return R.drawable.twoh
        if(face==2&&suit==4)return R.drawable.twos
        if(face==3&&suit==1)return R.drawable.threec
        if(face==3&&suit==2)return R.drawable.threed
        if(face==3&&suit==3)return R.drawable.threeh
        if(face==3&&suit==4)return R.drawable.threes
        if(face==4&&suit==1)return R.drawable.fourc
        if(face==4&&suit==2)return R.drawable.fourd
        if(face==4&&suit==3)return R.drawable.fourh
        if(face==4&&suit==4)return R.drawable.fours
        if(face==5&&suit==1)return R.drawable.fivec
        if(face==5&&suit==2)return R.drawable.fived
        if(face==5&&suit==3)return R.drawable.fiveh
        if(face==5&&suit==4)return R.drawable.fives
        if(face==6&&suit==1)return R.drawable.sixc
        if(face==6&&suit==2)return R.drawable.sixd
        if(face==6&&suit==3)return R.drawable.sixh
        if(face==6&&suit==4)return R.drawable.sixs
        if(face==7&&suit==1)return R.drawable.sevenc
        if(face==7&&suit==2)return R.drawable.sevend
        if(face==7&&suit==3)return R.drawable.sevenh
        if(face==7&&suit==4)return R.drawable.sevens
        if(face==8&&suit==1)return R.drawable.eightc
        if(face==8&&suit==2)return R.drawable.eightd
        if(face==8&&suit==3)return R.drawable.eighth
        if(face==8&&suit==4)return R.drawable.eights
        if(face==9&&suit==1)return R.drawable.ninec
        if(face==9&&suit==2)return R.drawable.nined
        if(face==9&&suit==3)return R.drawable.nineh
        if(face==9&&suit==4)return R.drawable.nines
        if(face==10&&suit==1)return R.drawable.tenc
        if(face==10&&suit==2)return R.drawable.tend
        if(face==10&&suit==3)return R.drawable.tenh
        if(face==10&&suit==4)return R.drawable.tens
        if(face==11&&suit==1)return R.drawable.jackc
        if(face==11&&suit==2)return R.drawable.jackd
        if(face==11&&suit==3)return R.drawable.jackh
        if(face==11&&suit==4)return R.drawable.jacks
        if(face==12&&suit==1)return R.drawable.queenc
        if(face==12&&suit==2)return R.drawable.queend
        if(face==12&&suit==3)return R.drawable.queenh
        if(face==12&&suit==4)return R.drawable.queens
        if(face==13&&suit==1)return R.drawable.kingc
        if(face==13&&suit==2)return R.drawable.kingd
        if(face==13&&suit==3)return R.drawable.kingh
        if(face==13&&suit==4)return R.drawable.kings
       return -1
    }
}